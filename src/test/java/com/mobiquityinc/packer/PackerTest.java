package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.mobiquityinc.packer.Packer.pack;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PackerTest {

    @Test
    void sampleTest01() throws APIException, IOException {
        String result = pack(getClass().getResource("/files/sample-test-01.txt").toString().replace("file:", ""));
        assertEquals("4", result);
    }
}
