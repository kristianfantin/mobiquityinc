package com.mobiquityinc.messages;

import lombok.Getter;

@Getter
public enum ApiMessages {

    IO_ERROR("Error IO"),
    INVALID_INPUT("Invalid Input of Data"),
    PACKAGE_MAX_WEIGHT_EXCEEDED("Max weight that a package can take is ≤ 100");

    private String message;

    ApiMessages(String message) {
        this.message = message;
    }

}
