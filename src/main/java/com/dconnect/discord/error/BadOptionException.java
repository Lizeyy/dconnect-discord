package com.dconnect.discord.error;

public class BadOptionException extends IllegalArgumentException {

    public BadOptionException(String message) {
        super(message);
    }
}
