package com.dconnect.discord.mapper;

import com.dconnect.client.protocol.domain.request.ConnectionQuitRequest;
import com.dconnect.discord.error.BadOptionException;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public class ConnectionQuitRequestMapper {

    public static ConnectionQuitRequest map(ChatInputInteractionEvent event) {
        return new ConnectionQuitRequest(
                getChannelId(event),
                getCreatedBy(event),
                getServerId(event)
        );
    }

    private static String getCreatedBy(ChatInputInteractionEvent event) {
        return event.getInteraction().getUser().getId().asString();
    }

    private static String getServerId(ChatInputInteractionEvent event) {
        return event.getInteraction().getGuildId().orElseThrow(() -> new BadOptionException("Brakujące id serwera")).asString();
    }

    private static String getChannelId(ChatInputInteractionEvent event) {
        return event.getInteraction().getChannelId().asString();
    }
}
