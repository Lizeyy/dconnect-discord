package com.dconnect.discord.listener;

import com.dconnect.discord.command.ErrorCommand;
import com.dconnect.discord.command.SlashCommands;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.bouncycastle.util.Strings;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class SlashCommandListener {

    private final List<SlashCommands> commands;

    public SlashCommandListener(List<SlashCommands> commands, GatewayDiscordClient client) {
        this.commands = commands;
        client.on(ChatInputInteractionEvent.class, this::handle).subscribe();
    }

    public Mono<Void> handle(ChatInputInteractionEvent event) {
        final SlashCommands slashCommand = commands.stream()
                .filter(command -> Strings.toLowerCase(command.getName()).equals(event.getCommandName()))
                .findFirst()
                .orElse(new ErrorCommand());
        return slashCommand.handle(event);
    }
}
