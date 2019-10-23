package com.mobiquityinc.messages;

import lombok.Getter;

@Getter
public enum ApiMessages {

    IO_ERROR("Error IO"),
    INVALID_INPUT("Invalid Input of Data"),
    PACKAGE_MAX_WEIGHT_EXCEEDED("Max weight that a package can take is ≤ 100"),
    THERE_MIGHT_BE_UP_15_ITEM_TO_CHOOSE("There might be up to 15 items you need to choose from");

    private String message;

    ApiMessages(String message) {
        this.message = message;
    }

}
