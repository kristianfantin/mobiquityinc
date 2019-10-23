package com.mobiquityinc.exception;

public class APIException extends Exception {

  public APIException(String message, Exception e) {
    super(message, e);
  }

}
