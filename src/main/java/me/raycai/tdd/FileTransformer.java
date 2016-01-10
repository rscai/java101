package me.raycai.tdd;

import java.io.*;
import java.util.Map;

/**
 * Created by kkppccdd on 2016/1/10.
 */
public class FileTransformer {

    private Map<String, String> column1Mapping;
    private Map<String, String> column2Mapping;
    private Map<String, String> column3Mapping;
    private Map<String, String> column4Mapping;

    public void initialize() {
        column1Mapping = Column1Dao.getInstance().loadMapping();
        column2Mapping = Column2Dao.getInstance().loadMapping();
        column3Mapping = Column3Dao.getInstance().loadMapping();
        column4Mapping = Column4Dao.getInstance().loadMapping();
    }

    public void transform(final String inputFilePath, final String outputFilePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))
        ) {
            int lineIndex = 0;
            while (reader.ready()) {
                String line = reader.readLine();
                lineIndex++;
                if (line.startsWith("#") && lineIndex == 1) {
                    // header
                    writer.write(line);
                } else if (line.startsWith("#") && lineIndex != 1) {
                    // footer
                    writer.write(line);
                } else {

                    // others are body

                    final int COLUMN_1_LENGTH = 10;
                    final int COLUMN_2_LENGTH = 10;
                    final int COLUMN_3_LENGTH = 8;
                    final int COLUMN_4_LENGTH = 12;

                    String inputValue1 = line.substring(0, COLUMN_1_LENGTH);
                    String inputValue2 = line.substring(COLUMN_1_LENGTH, COLUMN_1_LENGTH + COLUMN_2_LENGTH);
                    String inputValue3 = line.substring(COLUMN_1_LENGTH + COLUMN_2_LENGTH, COLUMN_1_LENGTH + COLUMN_2_LENGTH + COLUMN_3_LENGTH);
                    String inputValue4 = line.substring(COLUMN_1_LENGTH + COLUMN_2_LENGTH + COLUMN_3_LENGTH, COLUMN_1_LENGTH + COLUMN_2_LENGTH + COLUMN_3_LENGTH + COLUMN_4_LENGTH);

                    // convert input domain to output domain

                    String outputValue1 = column1Mapping.containsKey(inputValue1) ? column1Mapping.get(inputValue1) : inputValue1;
                    String outputValue2 = column2Mapping.containsKey(inputValue2) ? column2Mapping.get(inputValue2) : inputValue2;
                    String outputValue3 = column3Mapping.containsKey(inputValue3) ? column3Mapping.get(inputValue3) : inputValue3;
                    String outputValue4 = column4Mapping.containsKey(inputValue4) ? column4Mapping.get(inputValue4) : inputValue4;

                    writer.write(outputValue1 + "|" + outputValue2 + "|" + outputValue3 + "|" + outputValue4);
                }
            }
        }
    }
    /**
    protected String transformBodyLine(final String inputBodyLine) {
        final int COLUMN_1_LENGTH = 10;
        final int COLUMN_2_LENGTH = 10;
        final int COLUMN_3_LENGTH = 8;
        final int COLUMN_4_LENGTH = 12;

        String inputValue1 = inputBodyLine.substring(0, COLUMN_1_LENGTH);
        String inputValue2 = inputBodyLine.esubstring(COLUMN_1_LENGTH, COLUMN_1_LENGTH + COLUMN_2_LENGTH);
        String inputValue3 = inputBodyLine.esubstring(COLUMN_1_LENGTH + COLUMN_2_LENGTH, COLUMN_1_LENGTH + COLUMN_2_LENGTH + COLUMN_3_LENGTH);
        String inputValue4 = inputBodyLine.substring(COLUMN_1_LENGTH + COLUMN_2_LENGTH + COLUMN_3_LENGTH, COLUMN_1_LENGTH + COLUMN_2_LENGTH + COLUMN_3_LENGTH + COLUMN_4_LENGTH);

        // convert input domain to output domain

        String outputValue1 = column1Mapping.containsKey(inputValue1) ? column1Mapping.get(inputValue1) : inputValue1;
        String outputValue2 = column2Mapping.containsKey(inputValue2) ? column2Mapping.get(inputValue2) : inputValue2;
        String outputValue3 = column3Mapping.containsKey(inputValue3) ? column3Mapping.get(inputValue3) : inputValue3;
        String outputValue4 = column4Mapping.containsKey(inputValue4) ? column4Mapping.get(inputValue4) : inputValue4;
        return outputValue1 + "|" + outputValue2 + "|" + outputValue3 + "|" + outputValue4
    }**/
}
