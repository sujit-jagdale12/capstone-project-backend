package com.ani.ems.exception;

public class DuplicateEmailFoundException extends RuntimeException {
    public DuplicateEmailFoundException(String msg) {
        super(msg);
    }
}
