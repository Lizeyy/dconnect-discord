package com.dconnect.discord.configuration;

import discord4j.common.JacksonResources;
import discord4j.discordjson.json.ApplicationCommandRequest;
import discord4j.rest.RestClient;
import discord4j.rest.service.ApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Slf4j
@Component
@AllArgsConstructor
public class DiscordGlobalSlashCommandRegistrar implements ApplicationRunner {

    private final RestClient restClient;

    @Override
    public void run(ApplicationArguments arguments) throws IOException {
        final ApplicationService applicationService = restClient.getApplicationService();
        final long applicationId = restClient.getApplicationId().block();
        log.info("Loading commands list for applicationId {}", applicationId);

        final JacksonResources d4jMapper = JacksonResources.create();
        final PathMatchingResourcePatternResolver matcher = new PathMatchingResourcePatternResolver();

        final List<ApplicationCommandRequest> commands = new ArrayList<>();
        for (Resource resource : matcher.getResources("commands/*.json")) {
            final ApplicationCommandRequest request = d4jMapper.getObjectMapper()
                    .readValue(resource.getInputStream(), ApplicationCommandRequest.class);

            commands.add(request);
        }
        log.info("Loaded commands list from file, size: {}", commands.size());

        applicationService.bulkOverwriteGlobalApplicationCommand(applicationId, commands)
                .doOnNext(command -> log.info("Successfully registered global command: {}", command.name()))
                .doOnError(command -> log.warn("Failed to register global command, error: {}", command.getMessage()))
                .subscribe();
    }
}
