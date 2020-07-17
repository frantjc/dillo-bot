package com.dillos.dillobot.exceptions;

public class InvalidCommandPrefixException extends Exception {

    private static final long serialVersionUID = 6061706031177740067L;

    public InvalidCommandPrefixException(String error) {
        super(error);
    }
}
