package com.dconnect.discord.command;

import com.dconnect.client.protocol.domain.response.ConnectionListOnServerResponse;
import com.dconnect.discord.error.BadOptionException;
import com.dconnect.discord.service.ConnectionService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class ConnectionsListOnServerCommand implements SlashCommands {

    private final ConnectionService connectionService;

    @Override
    public String getName() {
        return CommandName.CONNECTIONS_LIST_ON_SERVER.name();
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        final ConnectionListOnServerResponse response = connectionService.getConnectionListOnServerResponse(
                event.getInteraction().getGuildId().orElseThrow(() -> new BadOptionException("Brakujące id serwera")).asString()
        );

        return event.reply().withContent("Lista połączeń na serwerze " + response.getServerName() + ": \n" + response.getConnections());
    }
}
