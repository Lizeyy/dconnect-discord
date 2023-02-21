package com.dconnect.discord.mapper;

import com.dconnect.client.protocol.domain.request.ConnectionCreateRequest;
import com.dconnect.discord.error.BadOptionException;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;

public class ConnectionCreateRequestMapper {

    public static ConnectionCreateRequest map(ChatInputInteractionEvent event) {
        return new ConnectionCreateRequest(
                getName(event),
                getCreatedBy(event),
                getServerId(event),
                getChannelId(event)
        );
    }

    private static String getName(ChatInputInteractionEvent event) {
        return event.getOption("nazwa")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .orElseThrow(() -> new BadOptionException("Brakująca nazwa połączenia"));
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
