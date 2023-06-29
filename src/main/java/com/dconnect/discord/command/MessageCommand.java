package com.dconnect.discord.command;

import com.dconnect.discord.mapper.MessageRequestMapper;
import com.dconnect.discord.service.ConnectionService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.spec.InteractionApplicationCommandCallbackSpec;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class MessageCommand implements SlashCommands {

    private final ConnectionService connectionService;

    @Override
    public String getName() {
        return CommandName.MESSAGE.name();
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        connectionService.sendMessage(MessageRequestMapper.map(event));
        return event.reply(InteractionApplicationCommandCallbackSpec.builder().content("Wysłano wiadomość").ephemeral(true).build());
    }
}
