package com.dconnect.discord.notification;

import com.dconnect.client.protocol.domain.notification.NewInvitation;
import com.dconnect.discord.domain.MessageBroadcast;
import discord4j.core.spec.EmbedCreateSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

import static com.dconnect.discord.configuration.EmbedProperties.BLUE;
import static com.dconnect.discord.configuration.EmbedProperties.EmbedMessage.NEW_INVITATION_TITLE;
import static com.dconnect.discord.configuration.EmbedProperties.EmbedMessage.NEW_MESSAGE_BROADCAST;
import static com.dconnect.discord.configuration.EmbedProperties.VIOLET;

@Component
@RequiredArgsConstructor
public class EmbedDispatcher implements EmbedVisitor {

    @Override
    public EmbedCreateSpec prepareEmbed(NewInvitation message) {
        return EmbedCreateSpec.builder()
                .color(BLUE)
                .author(message.getServerName(), null, "https://cdn.discordapp.com/icons/" + message.getServerId() + "/" + message.getIconUrl())
                .title(NEW_INVITATION_TITLE)
                .addField("Połączenie: ", message.getConnectionName(), false)
                .addField("Serwer: ", message.getServerName(), false)
                .addField("Od: ", message.getCreatedByName(), false)
                .addField("Stworzone: ", message.getCreationDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")), false)
                .timestamp(Instant.now())
                .build();
    }

    @Override
    public EmbedCreateSpec prepareEmbed(MessageBroadcast message) {
        return EmbedCreateSpec.builder()
                .color(VIOLET)
                .author(message.getUsername(), null, "https://cdn.discordapp.com/avatars/" + message.getUserId() + "/" + message.getAvatarId())
                .title(NEW_MESSAGE_BROADCAST + message.getUsername())
                .addField("Wiadomość: ", message.getMessage(), false)
                .addField("Z serwera: ", message.getServerName(), false)
                .timestamp(Instant.now())
                .build();
    }
}
