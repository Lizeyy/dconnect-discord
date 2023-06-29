package com.dconnect.discord.service;

import com.dconnect.client.protocol.domain.response.MessageSend;
import com.dconnect.client.protocol.domain.response.UserInfo;
import com.dconnect.discord.domain.MessageBroadcast;
import com.dconnect.discord.notification.EmbedVisitor;
import discord4j.core.spec.EmbedCreateSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BroadcastMessageService {

    private final EmbedVisitor visitor;
    private final MessageService messageService;
    private final DiscordApiService discordApiService;

    public void broadcastMessage(MessageSend message) {
        final EmbedCreateSpec embed = visitor.prepareEmbed(createMessageBroadcast(message));
        message.getChannelsId().forEach(channel -> messageService.sendBroadcastMessage(embed, channel));
    }

    private MessageBroadcast createMessageBroadcast(MessageSend messageSend) {
        final UserInfo userInfo = discordApiService.getUserInfo(messageSend.getUserId());
        return MessageBroadcast.builder()
                .userId(messageSend.getUserId())
                .username(userInfo.getUsername())
                .avatarId(userInfo.getAvatar())
                .serverName(messageSend.getServerRoot())
                .message(messageSend.getMessage())
                .build();
    }



}
