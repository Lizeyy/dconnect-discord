package com.dconnect.discord.endpoint;

import com.dconnect.discord.service.ConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConnectionController {

    private final ConnectionService connectionService;
}
