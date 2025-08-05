package org.example.services.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.domain.dto.requests.WorkspaceCreateRequest;
import org.example.domain.entities.UserEntity;
import org.example.domain.events.StringEvent;
import org.example.repositories.WorkspaceRepository;
import org.example.services.WorkspaceService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserEventsListener {

    private final WorkspaceService workspaceService;
    private final WorkspaceRepository workspaceRepository;

    @KafkaListener(topics = "user-events", groupId = "space-service")
    public void handleUserCreated(StringEvent event) {
        log.info("event type: {} \n payload: {}", event.getEventType(), event.getPayload());
        Long userId = Long.valueOf(event.getPayload());

        WorkspaceCreateRequest request = WorkspaceCreateRequest.builder()
                .workspaceName("test-workspace")
                .workspaceDescription("Привет! Это твоё тестовое пространство. " +
                        "Можешь экспериментировать с ним как тебе будет угодно")
                .taskPrefix(RandomStringUtils.randomAlphabetic(2, 6).toUpperCase())
                .build();

        while (workspaceRepository.existsByTaskPrefix(request.getTaskPrefix())) {
            request.setTaskPrefix(RandomStringUtils.randomAlphabetic(2, 6).toUpperCase());
        }

        UserEntity userEntityIdOnly = UserEntity.builder().userId(userId).build();
        workspaceService.createWorkspace(request, userEntityIdOnly);
    }
}

