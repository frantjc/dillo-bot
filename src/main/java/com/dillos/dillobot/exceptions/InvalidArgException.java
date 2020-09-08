package com.dillos.dillobot.exceptions;

public class InvalidArgException extends Exception {

  private static final long serialVersionUID = -1867078925544380335L;

  public InvalidArgException(String error) {
    super(error);
  }
}
