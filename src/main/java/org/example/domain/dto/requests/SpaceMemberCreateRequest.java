package org.example.domain.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.example.domain.entities.SpaceMemberEntity;


@Data
@Schema(description = "Запрос на создание нового участника рабочего пространства")
public class SpaceMemberCreateRequest {
    @Schema(description = "id пользователя, которого нужно добавить")
    @NotBlank(message = "id пользователя не должен быть пустым")
    private Long memberId;

    @Schema(description = "Роль пользователя, OWNER, READER или MEMBER", example = "OWNER")
    @NotBlank(message = "Роль пользователя не должна быть пустой")
    private SpaceMemberEntity.Role role;
}