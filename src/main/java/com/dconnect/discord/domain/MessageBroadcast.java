package com.dconnect.discord.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageBroadcast {

    private String userId;
    private String username;
    private String avatarId;
    private String serverName;
    private String message;
}
