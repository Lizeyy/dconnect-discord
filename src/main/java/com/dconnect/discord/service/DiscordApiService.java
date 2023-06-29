package com.dconnect.discord.service;

import com.dconnect.client.protocol.domain.response.ChannelInfo;
import com.dconnect.client.protocol.domain.response.ServerInfo;
import com.dconnect.client.protocol.domain.response.UserInfo;
import com.dconnect.discord.configuration.CustomRestTemplate;
import discord4j.core.object.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.dconnect.client.protocol.domain.DiscordApiPath.*;

@Service
@RequiredArgsConstructor
public class DiscordApiService {

    private final CustomRestTemplate restTemplate;

    public ServerInfo getServerInfo(String serverId) {
        final URI uri = URI.create(GET_SERVER + serverId);
        return restTemplate.exchange(uri, HttpMethod.GET, restTemplate.getCustomHeaders(), ServerInfo.class).getBody();
    }

    public ChannelInfo getChannelInfo(String channelId) {
        final URI uri = URI.create(GET_CHANNEL + channelId);
        return restTemplate.exchange(uri, HttpMethod.GET, restTemplate.getCustomHeaders(), ChannelInfo.class).getBody();
    }

    public UserInfo getUserInfo(String userId) {
        final URI uri = URI.create(GET_USER + userId);
        return restTemplate.exchange(uri, HttpMethod.GET, restTemplate.getCustomHeaders(), UserInfo.class).getBody();
    }
}
