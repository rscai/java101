package me.raycai.tdd.mocking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


/**
 * Created by Ray Cai on 2016/2/4.
 */
public class TransformerTest {

    private Transformer testObject;

    @Before
    public void setUp() throws Exception {
        testObject = new Transformer();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testStubReturnValue() throws Exception {
        ColumnMappingDao mockColumnMappingDao = mock(ColumnMappingDao.class);

        when(mockColumnMappingDao.map(anyString())).thenReturn("ADD");
        testObject.setColumnMappingDao(mockColumnMappingDao);

        FileWriter mockFileWriter = mock(FileWriter.class);

        testObject.setFileWriter(mockFileWriter);

        final String input = "A";
        final String expectedOutput = "ADD";

        final String actualOutput = testObject.transform(input);

        assertThat(actualOutput, is(expectedOutput));
    }

    @Test
    public void testCustomerAnswer() throws Exception {
        ColumnMappingDao mockColumnMappingDao = mock(ColumnMappingDao.class);
        final Map<String, String> codeMapping = new HashMap<>();
        codeMapping.put("A", "ADD");
        codeMapping.put("123", "COMMON-STOCK");
        codeMapping.put("O_B", "OPEN_BID");
        when(mockColumnMappingDao.map(anyString())).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
                final String argument = (String) invocationOnMock.getArguments()[0];
                final String mappedCode = codeMapping.get(argument);

                return mappedCode;
            }
        });
        
        testObject.setColumnMappingDao(mockColumnMappingDao);

        final FileWriter mockFileWriter = mock(FileWriter.class);
        testObject.setFileWriter(mockFileWriter);

        final String input = "A|123|O_B";
        final String expectedOutput = "ADD|COMMON-STOCK|OPEN_BID";
        final String actualOutput = testObject.transform(input);

        assertThat(actualOutput, is(expectedOutput));


    }

    @Test
    public void testVerifyBehavior() throws Exception {
        ColumnMappingDao mockColumnMappingDao = mock(ColumnMappingDao.class);
        final Map<String, String> codeMapping = new HashMap<>();
        codeMapping.put("A", "ADD");
        codeMapping.put("123", "COMMON-STOCK");
        codeMapping.put("O_B", "OPEN_BID");
        when(mockColumnMappingDao.map(anyString())).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
                final String argument = (String) invocationOnMock.getArguments()[0];
                final String mappedCode = codeMapping.get(argument);

                return mappedCode;
            }
        });

        testObject.setColumnMappingDao(mockColumnMappingDao);

        final FileWriter mockFileWriter = mock(FileWriter.class);
        testObject.setFileWriter(mockFileWriter);

        final String input = "A|123|O_B";
        final String expectedOutput = "ADD|COMMON-STOCK|OPEN_BID";
        final String actualOutput = testObject.transform(input);

        assertThat(actualOutput, is(expectedOutput));

        // verify behavior
        verify(mockFileWriter, times(1)).write(anyString());

        // verify behavior with argument matching
        ArgumentCaptor<String> writeContentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockFileWriter, times(1)).write(writeContentCaptor.capture());

        assertThat(writeContentCaptor.getValue(), is(expectedOutput));


    }

}