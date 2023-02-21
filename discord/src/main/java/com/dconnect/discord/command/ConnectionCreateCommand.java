package com.dconnect.discord.command;

import com.dconnect.client.protocol.domain.response.ConnectionCreateResponse;
import com.dconnect.discord.mapper.ConnectionCreateRequestMapper;
import com.dconnect.discord.service.ConnectionService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class ConnectionCreateCommand implements SlashCommands {

    private final ConnectionService connectionService;

    @Override
    public String getName() {
        return CommandName.CONNECTION_CREATE.name();
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        final ConnectionCreateResponse response = connectionService.createConnection(ConnectionCreateRequestMapper.map(event));

        return event.reply().withContent("Utworzono nowe połączenie: " + response.getName() + "\n" +
                "Token do zaproszeń: " + response.getJoinToken());
    }
}
