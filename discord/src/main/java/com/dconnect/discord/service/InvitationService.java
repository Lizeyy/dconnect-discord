package com.dconnect.discord.service;

import com.dconnect.client.protocol.domain.request.InvitationRequest;
import com.dconnect.client.protocol.domain.response.InvitationResponse;
import com.dconnect.discord.client.InfrastructureClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvitationService {

    private final InfrastructureClient infrastructureClient;

    public InvitationResponse cancelInvitation(InvitationRequest request) {
        return infrastructureClient.cancelInvitation(request);
    }

    public InvitationResponse acceptInvitation(InvitationRequest request) {
        return infrastructureClient.acceptInvitation(request);
    }


}
