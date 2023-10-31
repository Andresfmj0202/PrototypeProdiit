package com.Prodiit.Prototype.Exceptions;

public class SiteNotFoundException extends RuntimeException {
    public SiteNotFoundException(String message) {
        super(message);
    }
}
