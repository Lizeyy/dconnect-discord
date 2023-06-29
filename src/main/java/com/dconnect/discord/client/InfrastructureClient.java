package com.dconnect.discord.client;

import com.dconnect.client.InfrastructureRestClient;
import com.dconnect.client.protocol.domain.request.*;
import com.dconnect.client.protocol.domain.response.*;
import com.dconnect.discord.error.ConnectionNotCreateException;
import com.dconnect.discord.error.ConnectionNotJoinException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InfrastructureClient {

    private final InfrastructureRestClient infrastructureRestClient;

    public ConnectionCreateResponse createConnection(ConnectionCreateRequest request) {
        try {
            return infrastructureRestClient.createConnection(request);
        } catch (FeignException.FeignClientException e) {
            throw new ConnectionNotCreateException("Nie udało się stworzyć nowego połączenia, problem z siecią");
        } catch (Exception e) {
            throw new ConnectionNotJoinException("Nie udało się stworzyć nowego połączenia");
        }
    }

    public ConnectionJoinResponse joinConnection(ConnectionJoinRequest request) {
        try {
            return infrastructureRestClient.joinConnection(request);
        } catch (FeignException.FeignClientException e) {
            throw new ConnectionNotCreateException("Nie udało się dołączyć do połączenia, problem z siecią");
        } catch (Exception e) {
            throw new ConnectionNotJoinException("Nie udało się dołączyć do połączenia");
        }
    }

    public ConnectionQuitResponse quitConnection(ConnectionQuitRequest request) {
        try {
            return infrastructureRestClient.quitConnection(request);
        } catch (FeignException.FeignClientException e) {
            throw new ConnectionNotCreateException("Nie udało się wyjść z  połączenia, problem z siecią");
        } catch (Exception e) {
            throw new ConnectionNotJoinException("Nie udało się wyjść z połączenia");
        }
    }

    public InvitationResponse cancelInvitation(InvitationRequest request) {
        try {
            return infrastructureRestClient.removeInvitation(request);
        } catch (FeignException.FeignClientException e) {
            throw new ConnectionNotCreateException("Nie udało się dołączyć do połączenia, problem z siecią");
        } catch (Exception e) {
            throw new ConnectionNotJoinException("Nie udało się dołączyć do połączenia");
        }
    }

    public InvitationResponse acceptInvitation(InvitationRequest request) {
        try {
            return infrastructureRestClient.addConnection(request);
        } catch (FeignException.FeignClientException e) {
            throw new ConnectionNotCreateException("Nie udało się dołączyć do połączenia, problem z siecią");
        } catch (Exception e) {
            throw new ConnectionNotJoinException("Nie udało się dołączyć do połączenia");
        }
    }

    public ConnectionListOnServerResponse getConnectionListOnServerResponse(String serverId) {
        try {
            return infrastructureRestClient.getConnectionListOnServerResponse(serverId);
        } catch (FeignException.FeignClientException e) {
            throw new ConnectionNotCreateException("Nie udało się pobrać listy połączeń serwera " + serverId + ", problem z siecią");
        } catch (Exception e) {
            throw new ConnectionNotJoinException("Nie udało się pobrać listy połączeń serwera " + serverId);
        }
    }

    public ConnectionServersListResponse getConnectionServersListResponse(String channelId) {
        try {
            return infrastructureRestClient.getConnectionServersListResponse(channelId);
        } catch (FeignException.FeignClientException e) {
            throw new ConnectionNotCreateException("Nie udało się pobrać listy serwerów w połączeniu na kanale " + channelId + ", problem z siecią");
        } catch (Exception e) {
            throw new ConnectionNotJoinException("Nie udało się pobrać listy serwerów w połączeniu na kanale " + channelId);
        }
    }

    public void sendMessage(MessageRequest request) {
        try {
            infrastructureRestClient.sendMessage(request);
        } catch (FeignException.FeignClientException e) {
            throw new ConnectionNotCreateException("Nie udało się wysłać wiadomości - kanał: " + request.getChannelId() + ", problem z siecią");
        } catch (Exception e) {
            throw new ConnectionNotJoinException("Nie udało się wysłać wiadomości - kanał: " + request.getChannelId());
        }
    }
}
