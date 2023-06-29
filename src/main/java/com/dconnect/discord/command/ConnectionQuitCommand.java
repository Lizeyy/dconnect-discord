package com.dconnect.discord.command;

import com.dconnect.client.protocol.domain.response.ConnectionQuitResponse;
import com.dconnect.discord.mapper.ConnectionQuitRequestMapper;
import com.dconnect.discord.service.ConnectionService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class ConnectionQuitCommand implements SlashCommands {

    private final ConnectionService connectionService;

    @Override
    public String getName() {
        return CommandName.CONNECTION_QUIT.name();
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        final ConnectionQuitResponse response = connectionService.quitConnection(ConnectionQuitRequestMapper.map(event));

        return event.reply().withContent("Opuszczono połączenie " + response.getConnectionName() + "\n" +
                " na serwerze " + response.getServerName());
    }
}
