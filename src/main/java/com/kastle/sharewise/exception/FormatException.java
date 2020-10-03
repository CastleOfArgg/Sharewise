package com.kastle.sharewise.exception;

public class FormatException extends RuntimeException {
    private static final long serialVersionUID = 2L;

    public FormatException() {
        super();
    }

    public FormatException(String msg) {
        super(msg);
    }
}
