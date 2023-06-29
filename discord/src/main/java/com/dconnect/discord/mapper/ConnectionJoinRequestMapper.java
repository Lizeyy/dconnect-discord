package com.dconnect.discord.mapper;

import com.dconnect.client.protocol.domain.request.ConnectionJoinRequest;
import com.dconnect.discord.error.BadOptionException;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public class ConnectionJoinRequestMapper {

    public static ConnectionJoinRequest map(ChatInputInteractionEvent event) {
        return new ConnectionJoinRequest(
                getToken(event),
                getCreatedBy(event),
                getServerId(event),
                getChannelId(event)
        );
    }

    private static String getToken(ChatInputInteractionEvent event) {
        return event.getOption("token")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .orElseThrow(() -> new BadOptionException("Brakujący token"));
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
