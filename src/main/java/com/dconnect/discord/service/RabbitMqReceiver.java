package com.dconnect.discord.service;

import com.dconnect.client.protocol.domain.response.MessageSend;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMqReceiver {

    private final BroadcastMessageService broadcastMessageService;

    @RabbitListener(queues = "messageQueue")
    private void receiveMessage(MessageSend message) {
        broadcastMessageService.broadcastMessage(message);
    }
}
