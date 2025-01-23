package com.gerenciador.tarefas.service;

import com.gerenciador.tarefas.entity.Task;
import com.gerenciador.tarefas.exceptions.NotPermittedDeleteException;
import com.gerenciador.tarefas.exceptions.NotPermittedUpdateTaskStatusException;
import com.gerenciador.tarefas.exceptions.TaskExistingException;
import com.gerenciador.tarefas.repository.TaskManagerRepository;
import com.gerenciador.tarefas.request.RegisterTaskRequest;
import com.gerenciador.tarefas.request.UpdateTaskRequest;
import com.gerenciador.tarefas.status.TaskStatusEnum;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class TaskManagerService {

    @Autowired
    private TaskManagerRepository taskManagerRepository;

    @Autowired
    private UserService userService;

    public Task saveTask(RegisterTaskRequest request) {

        Task taskValidation = taskManagerRepository.findByTitleOrDescription(request.getTitle(), request.getDescription());

        if(taskValidation != null) {
            throw new TaskExistingException("There is already a task with the same title or description");
        }

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .taskStatus(TaskStatusEnum.CREATE)
                .creator(userService.findUserById(request.getCreatorId()).get())
                .numberEstimatedHours(request.getNumberEstimatedHours()).build();

        return this.taskManagerRepository.save(task);

    }

    public Page<Task> getTaskByTitle(String title, Pageable pageable) {
        return this.taskManagerRepository.findByTitleContaining(title, pageable);
    }

    public Page<Task> getAllTask(Pageable pageable) {
        return this.taskManagerRepository.findAll(pageable);
    }

    public Task updateTask(Long id, UpdateTaskRequest request) {

        Task task = this.taskManagerRepository.findById(id).get();

        if (task.getTaskStatus().equals(TaskStatusEnum.FINISHED)) {
            throw new NotPermittedUpdateTaskStatusException("Not allowed task that is finished");
        }

        if (task.getTaskStatus().equals(TaskStatusEnum.CREATE) && request.getTaskStatus().equals(TaskStatusEnum.FINISHED)) {
            throw new NotPermittedUpdateTaskStatusException("Not allowed to move task to finished if it is in created state of CREATE");
        }

        if (task.getTaskStatus().equals(TaskStatusEnum.BLOCKED) && request.getTaskStatus().equals(TaskStatusEnum.FINISHED)) {
            throw new NotPermittedUpdateTaskStatusException("Not allowed to move task to finished if it is in created state of BLOCKED");
        }

        task.setTitle(request.getTitle());
        task.setTaskStatus(request.getTaskStatus());
        task.setDescription(request.getDescription());
        task.setResponsible(userService.findUserById(request.getResponsibleId()).get());
        task.setNumberEstimatedHours(request.getNumberEstimatedHours());
        task.setNumberRealizeHours(request.getNumberRealizeHours());

        this.taskManagerRepository.save(task);

        return task;
    }

    public void deleteTask(Long id) {

        Task task = this.taskManagerRepository.findById(id).get();

        if(!TaskStatusEnum.CREATE.equals(task.getTaskStatus())) {
            throw new NotPermittedDeleteException();
        }

        this.taskManagerRepository.deleteById(id);
    }
}
