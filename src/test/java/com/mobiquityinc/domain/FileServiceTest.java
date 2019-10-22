package com.mobiquityinc.domain;

import com.mobiquityinc.domain.service.FileService;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.template.MainTemplate;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static com.mobiquityinc.template.MainTemplate.challenge;

class FileServiceTest {

    @Test
    void sampleTest() throws IOException, APIException {
        InputStream inputStream = getClass().getResource("/files/sample-test.txt").openStream();

        new MainTemplate()
                .maxWeight(81,
                        challenge(1, 53.38, 45),
                        challenge(2, 88.62, 98),
                        challenge(3, 78.48,  3),
                        challenge(4, 72.30, 76),
                        challenge(5, 30.18,  9),
                        challenge(6, 46.34, 48)
                )
                .maxWeight(8,
                        challenge(1, 15.3, 34)
                )
                .maxWeight(75,
                        challenge(1, 85.31, 29),
                        challenge(2, 14.55, 74),
                        challenge(3,  3.98, 16),
                        challenge(4, 26.24, 55),
                        challenge(5, 63.69, 52),
                        challenge(6, 76.25, 75),
                        challenge(7, 60.02, 74),
                        challenge(8, 93.18, 35),
                        challenge(9, 89.95, 78)
                )
                .maxWeight(56,
                        challenge(1, 90.72, 13),
                        challenge(2, 33.80, 40),
                        challenge(3, 43.15, 10),
                        challenge(4, 37.97, 16),
                        challenge(5, 46.81, 36),
                        challenge(6, 48.77, 79),
                        challenge(7, 81.80, 45),
                        challenge(8, 19.36, 79),
                        challenge(9,  6.76, 64)
                )
                .check(new FileService(inputStream).load())
        ;
    }

    @Test
    void sampleTest01() throws IOException, APIException {
        InputStream inputStream = getClass().getResource("/files/sample-test-01.txt").openStream();

        new MainTemplate()
                .maxWeight(81,
                        challenge(1, 53.38, 45),
                        challenge(2, 88.62, 98),
                        challenge(3, 78.48,  3),
                        challenge(4, 72.30, 76),
                        challenge(5, 30.18,  9),
                        challenge(6, 46.34, 48)
                )
                .check(new FileService(inputStream).load())
        ;
    }

}
