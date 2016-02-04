package me.raycai.tdd.mocking;

/**
 * Created by Ray Cai on 2016/2/4.
 */
public class Transformer {
    private ColumnMappingDao columnMappingDao;

    private FileWriter fileWriter;

    public ColumnMappingDao getColumnMappingDao() {
        return columnMappingDao;
    }

    public void setColumnMappingDao(ColumnMappingDao columnMappingDao) {
        this.columnMappingDao = columnMappingDao;
    }

    public FileWriter getFileWriter() {
        return fileWriter;
    }

    public void setFileWriter(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    public String transform(final String input) {
        final String[] inputCodes = input.split("\\|");
        final String[] outputCodes = new String[inputCodes.length];
        for (int i = 0; i < inputCodes.length; i++) {
            String inputCode = inputCodes[i];
            String outputCode = columnMappingDao.map(inputCode);
            outputCodes[i] = outputCode;
        }
        final StringBuilder outputBuilder = new StringBuilder();
        for (int index = 0; index < outputCodes.length; index++) {
            outputBuilder.append(outputCodes[index]);
            outputBuilder.append("|");
        }
        if (outputBuilder.length() > 0) {
            outputBuilder.deleteCharAt(outputBuilder.length() - 1);
        }
        final String output = outputBuilder.toString();

        fileWriter.write(output);

        return output;
    }
}
