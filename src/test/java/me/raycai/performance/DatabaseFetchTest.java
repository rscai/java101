package me.raycai.performance;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by kkppccdd on 2016/12/24.
 */
@RunWith(Parameterized.class)
public class DatabaseFetchTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseFetchTest.class);
    private int batchSize;

    private long startTimestamp;
    private long maxObjectSize = 0;

    public DatabaseFetchTest(final int batchSize) {
        this.batchSize = batchSize;

    }

    @Parameterized.Parameters(name = "Fetch in batch size {0}")
    public static Collection parameterList() {
        final int maxBatchSize = 10000;
        final int parameterCount = 1000;
        return IntStream.generate(() -> ((ThreadLocalRandom.current()
                .nextInt(maxBatchSize) + 1)))
                .limit(parameterCount)
                .mapToObj(x -> new Object[]{x})
                .collect(Collectors.toList());
    }

    @BeforeClass
    public static void initialize() throws Exception {
        initLogger();
        final String insertSql = "INSERT INTO fetch_test(ID,NUM_COL,STR_COL) VALUES(?,?,?)";
        final int insertVolume = 1000000;
        final int maxNum = 1000000;

        try (final Connection conn = getConnection();
             final PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            pstmt.setFetchSize(5000);
            int count = 0;
            for (int i = 0; i < insertVolume; i++) {
                pstmt.setInt(1, i);
                pstmt.setInt(2, ThreadLocalRandom.current()
                        .nextInt(maxNum));
                pstmt.setString(3, ThreadLocalRandom.current()
                        .nextInt(maxNum) + "");
                pstmt.addBatch();
                count = count + 1;
                if (count % 100000 == 0) {
                    pstmt.executeBatch();
                }
            }

            pstmt.executeBatch();

            conn.commit();
        }
    }

    @AfterClass
    public static void destroy() throws Exception {
        final String cleanSql = "DELETE FROM FETCH_TEST";
        try (final Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(cleanSql);
        }
    }

    @Before
    public void setUp() {
        maxObjectSize = 0;
    }

    @After
    public void tearDown() {


    }

    @Test
    public void testDatabaseFetch() throws Exception {
        final String querySql = "SELECT ID, NUM_COL, STR_COL FROM fetch_test";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {
            stmt.setFetchSize(batchSize);

            startTimestamp = System.currentTimeMillis();


            try (ResultSet resultSet = stmt.executeQuery(querySql)) {

                resultSet.setFetchSize(batchSize);

                while (resultSet.next()) {
                    resultSet.getLong(1);
                    resultSet.getInt(2);
                    resultSet.getString(3);
                }

                final long elapsed = System.currentTimeMillis() - startTimestamp;

                LOGGER.info(String.format("batchSize: %d, elapsed: %d", batchSize, elapsed));
            }
        }

    }

    private static Connection getConnection() throws Exception {
        final String mysqlJdbcDriverClassName = "org.postgresql.Driver";
        final String databaseUrl = "jdbc:postgresql://192.168.56.101:5432/dev";
        final String databaseUsername = "dev";
        final String databasePassword = "dev";

        Class.forName(mysqlJdbcDriverClassName);

        final Connection conn = DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword);
        conn.setAutoCommit(false);

        return conn;
    }

    private static void initLogger() {
        FileAppender fa = new FileAppender();
        fa.setName("FileLogger");
        fa.setFile("/tmp/DatabaseFetchTest.log");
        fa.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
        fa.setThreshold(Level.INFO);
        fa.setAppend(true);
        fa.activateOptions();

        //add appender to any Logger (here is root)
        org.apache.log4j.Logger.getRootLogger()
                .addAppender(fa);
    }

    private void collectObjectSize(final Object object) {
        final long actualSize = Runtime.getRuntime()
                .totalMemory();
        if (actualSize > maxObjectSize) {
            maxObjectSize = actualSize;
        }
    }


}
