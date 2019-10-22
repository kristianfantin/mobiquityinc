package com.mobiquityinc.messages;

import lombok.Getter;

@Getter
public enum ErrorMessages {

    IO_ERROR("Error IO");

    private String message;

    ErrorMessages(String message) {
        this.message = message;
    }

}
