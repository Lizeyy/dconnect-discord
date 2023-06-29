package com.dconnect.discord.command;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ErrorCommand implements SlashCommands {
    @Override
    public String getName() {
        return CommandName.ERROR.name();
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return event.reply().withEphemeral(true).withContent("Wystąpił błąd :(");
    }
}
