package com.dconnect.discord.button;

import discord4j.core.event.domain.interaction.ButtonInteractionEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ErrorButton implements ButtonsAction {
    @Override
    public String getName() {
        return ButtonName.ERROR.value;
    }

    @Override
    public Mono<Void> handle(ButtonInteractionEvent event) {
        return event.reply().withEphemeral(true).withContent("Wystąpił błąd :(");
    }
}
