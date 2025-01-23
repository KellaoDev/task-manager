package com.gerenciador.tarefas.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Builder
public class RegisterTaskRequest {

    @NotBlank(message = "{register.task.request.title.required}")
    private String title;
    @Length(max = 150, message = "{register.task.request.description.limit}")
    private String description;
    @NotNull(message = "{register.task.request.creatorId.required}")
    private Long creatorId;
    @NotNull(message = "{register.task.request.numberEstimatedHours.required}")
    private Integer numberEstimatedHours;

}
