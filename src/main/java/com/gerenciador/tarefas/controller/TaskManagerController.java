package com.gerenciador.tarefas.controller;

import com.gerenciador.tarefas.entity.Task;
import com.gerenciador.tarefas.request.RegisterTaskRequest;
import com.gerenciador.tarefas.request.UpdateTaskRequest;
import com.gerenciador.tarefas.response.CreateTaskResponse;
import com.gerenciador.tarefas.response.GetTasksPagedResponse;
import com.gerenciador.tarefas.response.GetTasksResponse;
import com.gerenciador.tarefas.response.UpdateTaskResponse;
import com.gerenciador.tarefas.service.TaskManagerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task-manager")
public class TaskManagerController {

    @Autowired
    private TaskManagerService taskManagerService;

    @PostMapping
    public ResponseEntity<CreateTaskResponse> saveTask(@Valid @RequestBody RegisterTaskRequest registerTaskRequest) {
        Task taskSave = taskManagerService.saveTask(registerTaskRequest);

        CreateTaskResponse response = CreateTaskResponse
                .builder()
                .id(taskSave.getId())
                .title(taskSave.getTitle())
                .description(taskSave.getDescription())
                .creator(taskSave.getCreator().getUsername())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<GetTasksPagedResponse> getAllTasks(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {

        Page<Task> tasksPaged = null;

        if (title != null) {
            tasksPaged = this.taskManagerService.getTaskByTitle(title, PageRequest.of(page, size));
        } else {
            tasksPaged = this.taskManagerService.getAllTask(PageRequest.of(page, size));
        }

        List<GetTasksResponse> tasks = tasksPaged
                .getContent()
                .stream()
                .map(task -> {
                    return GetTasksResponse
                            .builder()
                            .id(task.getId())
                            .title(task.getTitle())
                            .description(task.getDescription())
                            .responsible(task.getResponsible() != null ? task.getResponsible().getUsername() : "NOT_ASSIGNED")
                            .creator(task.getCreator().getUsername())
                            .taskStatus(task.getTaskStatus())
                            .numberEstimatedHours(task.getNumberEstimatedHours())
                            .numberRealizeHours(task.getNumberRealizeHours())
                            .dateRegister(task.getDateRegister())
                            .dateUpdate(task.getDateUpdate())
                            .build();
                }).toList();


        GetTasksPagedResponse response = GetTasksPagedResponse
                .builder()
                .pageCurrent(tasksPaged.getNumber())
                .totalPages(tasksPaged.getTotalPages())
                .totalItems(tasksPaged.getTotalElements())
                .tasks(tasks)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UpdateTaskResponse> updateTask(@PathVariable Long id, @RequestBody UpdateTaskRequest request) {

        Task taskUpdate = taskManagerService.updateTask(id, request);

        UpdateTaskResponse response = UpdateTaskResponse
                .builder()
                .id(taskUpdate.getId())
                .title(taskUpdate.getTitle())
                .description(taskUpdate.getDescription())
                .creator(taskUpdate.getCreator().getUsername())
                .responsible(taskUpdate.getResponsible().getUsername())
                .taskStatus(taskUpdate.getTaskStatus().toString())
                .numberEstimatedHours(taskUpdate.getNumberEstimatedHours())
                .numberRealizeHours(taskUpdate.getNumberRealizeHours() != null ? taskUpdate.getNumberRealizeHours() : null)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTask(@PathVariable Long id) {
        this.taskManagerService.deleteTask(id);
    }

}
