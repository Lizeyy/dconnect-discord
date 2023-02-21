package com.dconnect.discord.endpoint;

import com.dconnect.client.protocol.domain.response.ServerInfo;
import com.dconnect.discord.service.DiscordApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ServerController {

    private final DiscordApiService discordApiService;

    @GetMapping("/api/discord/server/{id}")
    public ServerInfo getServerInfo(@PathVariable("id") String serverId) {
        return discordApiService.getServerInfo(serverId);
    }
}
