package org.example.domain.dto.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.domain.entities.SpaceMemberEntity;


@Data
@Schema(description = "Запрос на создание нового участника рабочего пространства")
public class SpaceMemberAddRequest {
    @Schema(description = "id пользователя в auth-service, которого нужно добавить")
    @NotNull(message = "id пользователя не должен быть пустым")
    private Long userId;

    @Schema(description = "Роль пользователя, OWNER, READER или MEMBER", example = "OWNER", implementation = SpaceMemberEntity.Role.class)
    @NotNull(message = "Роль пользователя не должна быть пустой")
    private SpaceMemberEntity.Role role;

    @JsonIgnore
    private Long invitedByMemberId;

    @JsonIgnore
    private Long workspaceId;
}