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

    @NotBlank(message = "{register.task.request.title.obrigatorio}")
    private String title;
    @Length(max = 50, message = "{register.task.request.description.limite}")
    private String description;
    private Long creatorId;
    @NotNull(message = "{register.task.request.numberEstimatedHours.obrigatorio}")
    private Integer numberEstimatedHours;

}
