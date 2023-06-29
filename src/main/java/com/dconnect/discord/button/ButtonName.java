package com.dconnect.discord.button;

public enum ButtonName {

    ACCEPT_INVITATION("acceptInvitation"),
    CANCEL_INVITATION("cancelInvitation"),
    ERROR("error");

    public final String value;

    ButtonName(String value) {
        this.value = value;
    }

}
