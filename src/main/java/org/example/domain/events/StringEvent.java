package org.example.domain.events;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.enums.EventType;

import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonTypeName("stringEvent")
public class StringEvent {
    private EventType eventType;
    private String payload;
    @Builder.Default
    private Instant occurredAt = Instant.now();
}
