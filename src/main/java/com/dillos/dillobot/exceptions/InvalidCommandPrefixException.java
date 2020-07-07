package com.dillos.dillobot.exceptions;

public class InvalidCommandPrefixException extends Exception {

    public InvalidCommandPrefixException(String error) {
        super(error);
    }
}