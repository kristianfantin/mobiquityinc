package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.messages.ErrorMessages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static com.mobiquityinc.packer.Packer.pack;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PackerTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String CONTENT_FILE = "file:";

    @Test
    void sampleTest01() throws APIException, IOException {
        String result = pack(getClass().getResource("/files/sample-test-01.txt").toString().replace(CONTENT_FILE, ""));
        assertEquals("4", result);
    }

    @Test
    void sampleTest02() throws APIException, IOException {
        String result = pack(getClass().getResource("/files/sample-test-02.txt").toString().replace(CONTENT_FILE, ""));
        assertEquals("-", result);
    }

    @Test
    void sampleTest03() throws APIException, IOException {
        String result = pack(getClass().getResource("/files/sample-test-03.txt").toString().replace(CONTENT_FILE, ""));
        assertEquals("2, 7", result);
    }

    @Test
    void sampleTest04() throws APIException, IOException {
        String result = pack(getClass().getResource("/files/sample-test-04.txt").toString().replace(CONTENT_FILE, ""));
        assertEquals("8, 9", result);
    }

    @Test
    void sampleAllTests() throws APIException, IOException {
        String waiting =
                "4" + LINE_SEPARATOR +
                "-" + LINE_SEPARATOR +
                "2, 7" + LINE_SEPARATOR +
                "8, 9";

        String result = pack(getClass().getResource("/files/sample-test.txt").toString().replace(CONTENT_FILE, ""));
        assertEquals(waiting, result);
    }

    @Test
    void shouldBeErrorOfFileNotFound() {
        FileNotFoundException fileNotFoundException = Assertions.assertThrows(
                FileNotFoundException.class,
                () -> pack("/files/sample.rem"),
                ""
        );
        assertEquals("/files/sample.rem (No such file or directory)", fileNotFoundException.getMessage());
    }

    @Test
    void shouldBeErrorOfDataIn() {
        String filename = getClass().getResource("/files/sample-test-error.txt").toString().replace(CONTENT_FILE, "");
        APIException apiException = Assertions.assertThrows(
                APIException.class,
                () -> pack(filename),
                ErrorMessages.INVALID_INPUT.getMessage()
        );
        assertEquals(ErrorMessages.INVALID_INPUT.getMessage(), apiException.getMessage());
    }

}
