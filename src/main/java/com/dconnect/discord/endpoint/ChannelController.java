package com.dconnect.discord.endpoint;

import com.dconnect.client.protocol.domain.response.ChannelInfo;
import com.dconnect.discord.service.DiscordApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChannelController {

    private final DiscordApiService discordApiService;

    @GetMapping("/api/discord/channel/{id}")
    public ChannelInfo getChannelInfo(@PathVariable("id") String channelId) {
        return discordApiService.getChannelInfo(channelId);
    }
}
