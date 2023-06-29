package com.dconnect.discord.command;

import com.dconnect.client.protocol.domain.response.ConnectionJoinResponse;
import com.dconnect.discord.mapper.ConnectionJoinRequestMapper;
import com.dconnect.discord.service.ConnectionService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class ConnectionJoinCommand implements SlashCommands {

    private final ConnectionService connectionService;

    @Override
    public String getName() {
        return CommandName.CONNECTION_JOIN.name();
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        final ConnectionJoinResponse response = connectionService.joinConnection(ConnectionJoinRequestMapper.map(event));

        return event.reply().withContent("Wysłano prośbę o dołączenie do " + response.getName());
    }
}
