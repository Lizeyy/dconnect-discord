package com.dconnect.discord.mapper;

import com.dconnect.client.protocol.domain.request.MessageRequest;
import com.dconnect.discord.error.BadOptionException;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public class MessageRequestMapper {

    public static MessageRequest map(ChatInputInteractionEvent event) {
        return MessageRequest.builder()
                .channelId(getChannelId(event))
                .message(getName(event))
                .user(getUser(event))
                .build();
    }

    private static String getName(ChatInputInteractionEvent event) {
        return event.getOption("text")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .orElseThrow(() -> new BadOptionException("Brak treści wiadomości"));
    }

    private static String getUser(ChatInputInteractionEvent event) {
        return event.getInteraction().getUser().getId().asString();
    }

    private static String getChannelId(ChatInputInteractionEvent event) {
        return event.getInteraction().getChannelId().asString();
    }
}
