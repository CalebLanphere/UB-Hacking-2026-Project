package com.calebandwill.webserver.Exceptions;

public class SetupFailureException extends RuntimeException {
    public SetupFailureException(String message) {
        super(message);
    }
}
