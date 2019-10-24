package com.mobiquityinc.controller;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.messages.ApiMessages;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputControllerTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String CONTENT_FILE = "file:";

    @Test
    void givenPackageWeightGreaterThanLimitResultsPatternMessage() throws APIException, IOException {
        String waiting =
                "4" + LINE_SEPARATOR +
                        ApiMessages.PACKAGE_MAX_WEIGHT_EXCEEDED.getMessage() + LINE_SEPARATOR +
                        "2, 7" + LINE_SEPARATOR +
                        "8, 9";
        String filename = getClass().getResource("/files/sample-test-package-weight.txt").toString().replace(CONTENT_FILE, "");
        String result = getController(filename);
        assertEquals(waiting, result);
    }

    @Test
    void givenPackageWithOneWeightItemAboveLimitTheItemShouldBeIgnored() throws APIException, IOException {
        String filename = getClass().getResource("/files/sample-test-item-above-weight.txt").toString().replace(CONTENT_FILE, "");
        String result = getController(filename);
        assertEquals("6, 8, 9", result);
    }

    @Test
    void givenPackageWithOneCostItemAboveLimitTheItemShouldBeIgnored() throws APIException, IOException {
        String filename = getClass().getResource("/files/sample-test-item-above-cost.txt").toString().replace(CONTENT_FILE, "");
        String result = getController(filename);
        assertEquals("6, 8, 9", result);
    }

    @Test
    void givenPackageWithOneCostLimitAndWeightLimitTheItemShouldBeReturned() throws APIException, IOException {
        String filename = getClass().getResource("/files/sample-test-item-limit-cost.txt").toString().replace(CONTENT_FILE, "");
        String result = getController(filename);
        assertEquals("10", result);
    }

    @Test
    void givenPackageWithOneCostLimitAndWeightLimitTheItemShouldBeReturnedWith3Results() throws APIException, IOException {
        String filename = getClass().getResource("/files/sample-test-item-limit-cost-3-results.txt").toString().replace(CONTENT_FILE, "");
        String result = getController(filename);
        assertEquals("2, 3, 4", result);
    }

    @Test
    void givenSampleTestWithOneMorePackageWith16ItemsThenTheLastOneNeedsReturnMessage() throws APIException, IOException {
        String waiting =
                "4" + LINE_SEPARATOR +
                        "-" + LINE_SEPARATOR +
                        "2, 7" + LINE_SEPARATOR +
                        "8, 9" + LINE_SEPARATOR +
                        ApiMessages.THERE_MIGHT_BE_UP_15_ITEM_TO_CHOOSE.getMessage();

        String result = getController(getClass().getResource("/files/sample-test-more-than-15-items.txt").toString().replace(CONTENT_FILE, ""));
        assertEquals(waiting, result);
    }

    private String getController(String filename) throws APIException, IOException {
        InputController controller = new InputController();
        return controller.getData(null, filename);
    }

}
