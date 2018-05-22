package com.example.demo.security.errors;

public class TokenValidationErrorException extends RuntimeException {
    public TokenValidationErrorException(String s) {
        super(s);
    }
}
