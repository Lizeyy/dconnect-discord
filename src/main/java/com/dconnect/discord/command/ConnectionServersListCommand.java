package com.dconnect.discord.command;

import com.dconnect.client.protocol.domain.response.ConnectionServersListResponse;
import com.dconnect.discord.service.ConnectionService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class ConnectionServersListCommand implements SlashCommands {

    private final ConnectionService connectionService;

    @Override
    public String getName() {
        return CommandName.CONNECTION_SERVERS_LIST.name();
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        final ConnectionServersListResponse response = connectionService.getConnectionServersListResponse(
                event.getInteraction().getChannelId().asString()
        );

        return event.reply().withContent("Lista serwerów w połączeniu " + response.getConnectionName() + ": \n" + response.getServers());
    }
}
