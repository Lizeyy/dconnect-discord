package com.dconnect.discord.error;

public class SendMessageException extends IllegalArgumentException {

    public SendMessageException(String message) {
        super(message);
    }
}
