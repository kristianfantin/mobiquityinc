package com.mobiquityinc.domain;

import com.mobiquityinc.domain.service.FileService;
import com.mobiquityinc.exception.APIException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;

import static com.mobiquityinc.messages.ApiMessages.IO_ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class FileServiceMockTest {

    @Test
    void shouldBeErrorOfIOException() throws IOException, APIException {
        InputStream inputStream = getClass().getResource("/files/sample-test-01.txt").openStream();
        FileService fileService = new FileService(inputStream);
        FileService spy = Mockito.spy(fileService);
        when(spy.getPackageChallenges(any())).thenThrow(new IOException());

        APIException apiException = Assertions.assertThrows(
                APIException.class,
                spy::load,
                IO_ERROR.getMessage()
        );

        assertEquals(IO_ERROR.getMessage(), apiException.getMessage());
    }

}
