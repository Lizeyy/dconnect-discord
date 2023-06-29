package com.dconnect.discord.listener;

import com.dconnect.discord.button.ButtonsAction;
import com.dconnect.discord.button.ErrorButton;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ButtonInteractionEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class ButtonInteractionListener {

    private final List<ButtonsAction> buttons;

    public ButtonInteractionListener(List<ButtonsAction> buttons, GatewayDiscordClient client) {
        this.buttons = buttons;
        client.on(ButtonInteractionEvent.class, this::handle).subscribe();
    }

    public Mono<Void> handle(ButtonInteractionEvent event) {
        final ButtonsAction buttonsAction = buttons.stream()
                .filter(button -> {
                    final String[] actionName = event.getCustomId().split("_");
                    return button.getName().equals(actionName[0]);
                })
                .findFirst()
                .orElse(new ErrorButton());
        return buttonsAction.handle(event);
    }
}
