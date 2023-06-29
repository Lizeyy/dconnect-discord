package com.dconnect.discord.endpoint;

import com.dconnect.client.protocol.domain.notification.NewInvitation;
import com.dconnect.discord.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/api/notification/private/new-invitation")
    public void sendPrivateNotification(@Valid @RequestBody NewInvitation message) {
        notificationService.sendMessage(message);
    }
}
