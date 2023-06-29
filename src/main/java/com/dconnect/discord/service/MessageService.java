package com.dconnect.discord.service;

import com.dconnect.discord.error.SendMessageException;
import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.GuildMessageChannel;
import discord4j.core.object.entity.channel.PrivateChannel;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.core.spec.MessageCreateSpec;
import discord4j.core.spec.MessageEditSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final GatewayDiscordClient gatewayDiscordClient;

    public void sendEmbedPrivate(MessageCreateSpec message, String sendTo) {
        try {
            final User user = gatewayDiscordClient.getUserById(Snowflake.of(sendTo)).block();
            Assert.notNull(user, "Nie udało się pobrać użytkownika");
            final PrivateChannel privateChannel = user.getPrivateChannel().block();
            privateChannel.createMessage(message).block();
        } catch (Exception e) {
            throw new SendMessageException("Nie udało się powysłać wiadomości " + e.getMessage());
        }
    }

    public void sendBroadcastMessage(EmbedCreateSpec embed, String channelToSend) {
        try {
            gatewayDiscordClient.getChannelById(Snowflake.of(channelToSend))
                    .ofType(GuildMessageChannel.class)
                    .flatMap(channel -> channel.createMessage(embed))
                    .block();
        } catch (Exception e) {
            throw new SendMessageException("Nie udało się powysłać wiadomości " + e.getMessage());
        }
    }

    public void removeButtons(Optional<Message> messageOptional, String text) {
        Assert.isTrue(messageOptional.isPresent(), "Nie udało się pobrać wiadomości");
        final Message message = messageOptional.get();
        message.edit(MessageEditSpec.builder()
                        .components(new ArrayList<>())
                        .contentOrNull(text)
                        .build())
                .block();
    }
}
