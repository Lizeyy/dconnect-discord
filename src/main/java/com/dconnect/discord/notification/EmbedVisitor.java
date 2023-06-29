package com.dconnect.discord.notification;

import com.dconnect.client.protocol.domain.notification.NewInvitation;
import com.dconnect.discord.domain.MessageBroadcast;
import discord4j.core.spec.EmbedCreateSpec;

public interface EmbedVisitor {
    EmbedCreateSpec prepareEmbed(NewInvitation message);
    EmbedCreateSpec prepareEmbed(MessageBroadcast message);
}
