package com.dconnect.discord.service;

import com.dconnect.client.protocol.domain.request.ConnectionCreateRequest;
import com.dconnect.client.protocol.domain.request.ConnectionJoinRequest;
import com.dconnect.client.protocol.domain.response.ConnectionCreateResponse;
import com.dconnect.client.protocol.domain.response.ConnectionJoinResponse;
import com.dconnect.discord.client.InfrastructureClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConnectionService {

    private final InfrastructureClient infrastructureClient;

    public ConnectionCreateResponse createConnection(ConnectionCreateRequest request) {
        return infrastructureClient.createConnection(request);
    }

    public ConnectionJoinResponse joinConnection(ConnectionJoinRequest request) {
        return infrastructureClient.joinConnection(request);
    }


}
