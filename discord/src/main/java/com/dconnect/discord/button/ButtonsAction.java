package com.dconnect.discord.button;

import discord4j.core.event.domain.interaction.ButtonInteractionEvent;
import reactor.core.publisher.Mono;

public interface ButtonsAction {
    String getName();
    Mono<Void> handle(ButtonInteractionEvent event);
}
