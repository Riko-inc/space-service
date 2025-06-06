package org.example.domain.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Запрос на создание нового пространства")
public class WorkspaceCreateRequest {
    @Schema(description = "Название пространства", example = "MyAwesomeWorkspace")
    @Size(min = 1, max = 255, message = "Название пространства должно быть не длиннее 255 символов")
    @NotBlank(message = "Название пространства не должно быть пустым")
    private String workspaceName;

    @Schema(description = "Описание пространства", example = "Здесь мы будем делать жоские классные штуки")
    private String workspaceDescription;

    @Schema(description = "Префикс для задач пространства", example = "DEV")
    @Size(min = 2, max = 6, message = "Префикс для задач не должен быть длиннее 5 символов")
    private String taskPrefix;
}
