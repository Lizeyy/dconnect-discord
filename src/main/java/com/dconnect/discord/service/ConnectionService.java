package com.dconnect.discord.service;

import com.dconnect.client.protocol.domain.request.ConnectionCreateRequest;
import com.dconnect.client.protocol.domain.request.ConnectionJoinRequest;
import com.dconnect.client.protocol.domain.request.ConnectionQuitRequest;
import com.dconnect.client.protocol.domain.request.MessageRequest;
import com.dconnect.client.protocol.domain.response.*;
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

    public ConnectionQuitResponse quitConnection(ConnectionQuitRequest request) {
        return infrastructureClient.quitConnection(request);
    }

    public ConnectionListOnServerResponse getConnectionListOnServerResponse(String serverId) {
        return infrastructureClient.getConnectionListOnServerResponse(serverId);
    }

    public ConnectionServersListResponse getConnectionServersListResponse(String channelId) {
        return infrastructureClient.getConnectionServersListResponse(channelId);
    }

    public void sendMessage(MessageRequest request) {
        infrastructureClient.sendMessage(request);
    }

}
