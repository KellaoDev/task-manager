package com.gerenciador.tarefas.request;

import com.gerenciador.tarefas.status.TaskStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UpdateTaskRequest {

    @NotBlank(message = "{update.task.request.title.required}")
    private String title;
    @Length(max = 150, message = "{update.task.request.description.limit}")
    private String description;
    private TaskStatusEnum taskStatus;
    private Long responsibleId;
    @NotNull(message = "{update.task.request.numberEstimatedHours.required}")
    private Integer numberEstimatedHours;
    private Integer numberRealizeHours;

}
