package com.dconnect.discord.configuration;

import discord4j.rest.util.Color;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public class EmbedProperties {

    public final static Color BLUE = Color.of(115, 165, 165);
    public final static Color VIOLET = Color.of(178, 102, 255);


    @NoArgsConstructor(access = AccessLevel.NONE)
    public class EmbedMessage {

        public final static String NEW_INVITATION_TITLE = "Nowa prośba o dołączenie do połączenia!";

        public final static String NEW_MESSAGE_BROADCAST = "Nowa wiadomość od: ";
    }

}



