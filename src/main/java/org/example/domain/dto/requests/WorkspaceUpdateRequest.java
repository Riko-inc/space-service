package org.example.domain.dto.requests;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.domain.dto.SpaceMemberDto;

import java.util.List;

@Data
@Schema(description = "Запрос на обновление существующего пространства")
public class WorkspaceUpdateRequest {
    @NotNull
    @Schema(description = "id пространства", example = "0")
    private Long workspaceId;

    @Schema(description = "Название пространства", example = "MyAwesomeWorkspace")
    @Size(min = 1, max = 255, message = "Название пространства должно быть не длиннее 255 символов")
    @NotBlank(message = "Название пространства не должно быть пустым")
    private String workspaceName;

    @Schema(description = "Описание пространства", example = "Здесь мы будем делать жоские классные штуки")
    private String workspaceDescription;

    @Schema(description = "Префикс для задач пространства", example = "DEV")
    @Size(min = 2, max = 6, message = "Префикс для задач не должен быть длиннее 5 символов")
    @NotBlank
    private String taskPrefix;

    @ArraySchema(
            schema = @Schema(implementation = SpaceMemberDto.class),
            arraySchema = @Schema(
                    description = "Список id членов пространства",
                    example = "[ { \"memberId\": 123, \"role\": \"MEMBER\" }, { \"memberId\": 456, \"role\": \"READER\" } ]"
            )
    )
    private List<SpaceMemberCreateRequest> members;
}
