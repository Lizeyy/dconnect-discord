package com.dconnect.discord.error;

public class ConnectionNotJoinException extends RuntimeException {

    public ConnectionNotJoinException() {}
    public ConnectionNotJoinException(String message) {
        super(message);
    }
}
