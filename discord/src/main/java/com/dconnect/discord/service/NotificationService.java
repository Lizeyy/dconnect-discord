package com.dconnect.discord.service;

import com.dconnect.client.protocol.domain.notification.NewInvitation;
import com.dconnect.discord.notification.ButtonVisitor;
import com.dconnect.discord.notification.EmbedVisitor;
import discord4j.core.spec.MessageCreateSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final MessageService messageService;
    private final EmbedVisitor visitor;
    private final ButtonVisitor buttonVisitor;

    public void sendMessage(NewInvitation message) {
        final MessageCreateSpec messageCreateSpec = MessageCreateSpec.builder()
                .addEmbed(visitor.prepareEmbed(message))
                .addComponent(buttonVisitor.prepareButtonsInvitation(message.getInvitationId()))
                .build();
        messageService.sendEmbedPrivate(messageCreateSpec, message.getServerConnectionOwnerId());
    }



}
