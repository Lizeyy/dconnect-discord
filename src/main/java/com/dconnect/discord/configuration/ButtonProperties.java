package com.dconnect.discord.configuration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public class ButtonProperties {

    @NoArgsConstructor(access = AccessLevel.NONE)
    public static class ButtonId {
        public final static String ACCEPT_INVITATION = "acceptInvitation_";
        public final static String CANCEL_INVITATION = "cancelInvitation_";
    }

    @NoArgsConstructor(access = AccessLevel.NONE)
    public static class Emoji {

        public final static String ACCEPT_EMOJI = "\u2714";
        public final static String CANCEL_EMOJI = "\u2716";
    }

}



