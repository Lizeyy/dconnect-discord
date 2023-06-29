package com.dconnect.discord.command;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TestCommand implements SlashCommands {

    @Override
    public String getName() {
        return CommandName.TEST.name();
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return event.reply().withContent("test odpowiedź");
    }
}
