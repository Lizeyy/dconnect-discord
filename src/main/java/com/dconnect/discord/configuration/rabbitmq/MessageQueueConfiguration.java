package com.dconnect.discord.configuration.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MessageQueueConfiguration {

    public static final String QUEUE = "messageQueue";
    public static final String EXCHANGE = "messageExchange";
    public static final String ROUTING_KEY = "messageRoutingKey";


    @Bean(QUEUE)
    public Queue messageQueue() {
        log.info("Creating queue: {}", QUEUE);
        return QueueBuilder.durable(QUEUE).build();
    }

    @Bean(EXCHANGE)
    public DirectExchange messageExchange() {
        log.info("Creating exchange: {}", EXCHANGE);
        return new DirectExchange(EXCHANGE);
    }

    @Bean("messageBinding")
    Binding messageBinding(
            @Qualifier(QUEUE) Queue queue,
            @Qualifier(EXCHANGE) DirectExchange exchange) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(ROUTING_KEY);
    }
}
