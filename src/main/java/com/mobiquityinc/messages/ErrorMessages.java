package com.mobiquityinc.messages;

import lombok.Getter;

@Getter
public enum ErrorMessages {

    IO_ERROR("Error IO"),
    INVALID_INPUT("Invalid Input of Data");

    private String message;

    ErrorMessages(String message) {
        this.message = message;
    }

}
