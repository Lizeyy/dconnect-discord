package com.dconnect.discord.error;

public class ConnectionNotCreateException extends RuntimeException {

    public ConnectionNotCreateException() {}
    public ConnectionNotCreateException(String message) {
        super(message);
    }
}
