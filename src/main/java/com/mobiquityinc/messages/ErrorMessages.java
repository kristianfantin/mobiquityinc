package com.mobiquityinc.messages;

import lombok.Getter;

@Getter
public enum ErrorMessages {

    CURRENCY_VALUE_ERROR("Error reading currency value from file"),
    IO_ERROR("Error IO");

    private String message;

    ErrorMessages(String message) {
        this.message = message;
    }

}
