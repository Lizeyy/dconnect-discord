package com.dconnect.discord.command;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import reactor.core.publisher.Mono;

public interface SlashCommands {
    String getName();
    Mono<Void> handle(ChatInputInteractionEvent event);
}
