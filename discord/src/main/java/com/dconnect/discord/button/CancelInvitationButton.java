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
public class CancelInvitationButton implements ButtonsAction {

    private final InvitationService invitationService;
    private final MessageService messageService;

    @Override
    public String getName() {
        return ButtonName.CANCEL_INVITATION.value;
    }

    @Override
    public Mono<Void> handle(ButtonInteractionEvent event) {
        final String[] customId = event.getCustomId().split("_");
        final InvitationResponse response = invitationService.cancelInvitation(InvitationRequest.builder()
                .invitationId(customId[1])
                .build()
        );
        messageService.removeButtons(event.getMessage(), "Usunięto zaproszenie \n Od: " + response.getServerName() + "\n Do połączenia: " + response.getConnectionName());
        return event.deferEdit();
    }
}
