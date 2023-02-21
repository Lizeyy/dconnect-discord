package com.dconnect.discord.client;


import com.dconnect.client.InfrastructureRestClient;
import com.dconnect.client.protocol.domain.request.ConnectionCreateRequest;
import com.dconnect.client.protocol.domain.request.ConnectionJoinRequest;
import com.dconnect.client.protocol.domain.response.ConnectionCreateResponse;
import com.dconnect.client.protocol.domain.response.ConnectionJoinResponse;
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
            throw new ConnectionNotCreateException("Nie udało się stworzyć nowe połączenia, problem z siecią");
        } catch (Exception e) {
            throw new ConnectionNotJoinException("Nie udało się stworzyć nowe połączenia");
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

}
