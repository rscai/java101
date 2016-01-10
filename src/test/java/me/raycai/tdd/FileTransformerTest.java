package me.raycai.tdd;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;


import java.io.FileInputStream;

/**
 * Created by Ray Cai on 2016/1/10.
 */
public class FileTransformerTest {
    @Test
    public void test_should_get_output_security_file_given_input_request_file_when_transform() throws Exception {
        final String inputFilePath = "src/test/resources/me/raycai/tdd/request.input.txt";
        final String outputFilePath = "/tmp/actual.security.output.txt";

        final String expectedOutputFilePath = "src/test/resources/me/raycai/tdd/security.output.txt";

        FileTransformer testObject = new FileTransformer();
        testObject.initialize();
        testObject.transform(inputFilePath, outputFilePath);

        String expectedContent = IOUtils.toString(new FileInputStream(expectedOutputFilePath));
        String actualContent = IOUtils.toString(new FileInputStream(outputFilePath));

        assertThat(actualContent, is(expectedContent));

    }

    /**
    @Test
    public void test_should_get_transformed_line_given_input_line_when_transform_body_line() throws Exception {
        final String inputBodyLine = "0001      1002      X2134     O         ";
        final String expectedBodyLine = "ISN|212234|NAM|OPEN";

        FileTransformer testObject = new FileTransformer();
        testObject.initialize();
        final String actualOutput = testObject.transformBodyLine(inputBodyLine);

        assertThat(actualOutput, is(expectedBodyLine));

    }**/


}
