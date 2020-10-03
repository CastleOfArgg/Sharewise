package com.kastle.sharewise.exception;

public class NotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String msg) {
        super(msg);
    }
}
