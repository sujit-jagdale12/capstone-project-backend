package com.ani.ems.exception;

public class NoBookingFoundException extends RuntimeException {
    public NoBookingFoundException(String msg){
        super(msg);
    }
}
