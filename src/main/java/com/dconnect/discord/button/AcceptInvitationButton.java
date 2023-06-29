package com.dconnect.discord.button;

import com.dconnect.client.protocol.domain.request.InvitationRequest;
import com.dconnect.client.protocol.domain.response.InvitationResponse;
import com.dconnect.discord.service.InvitationService;
import com.dconnect.discord.service.MessageService;
import discord4j.core.event.domain.interaction.ButtonInteractionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AcceptInvitationButton implements ButtonsAction {

    private final InvitationService invitationService;
    private final MessageService messageService;

    @Override
    public String getName() {
        return ButtonName.ACCEPT_INVITATION.value;
    }

    @Override
    public Mono<Void> handle(ButtonInteractionEvent event) {
        final String[] customId = event.getCustomId().split("_");
        final InvitationResponse response = invitationService.acceptInvitation(InvitationRequest.builder()
                .invitationId(customId[1])
                .build());
        messageService.removeButtons(event.getMessage(), "Zaakceptowano zaproszenie \n Od: " + response.getServerName() + "\n Do połączenia: " + response.getConnectionName());
        return event.deferEdit();
    }
}
