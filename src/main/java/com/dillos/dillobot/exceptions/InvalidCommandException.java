package com.dillos.dillobot.exceptions;

public class InvalidCommandException extends Exception {

  private static final long serialVersionUID = 6061706031177740067L;

  public InvalidCommandException(String error) {
    super(error);
  }
}
