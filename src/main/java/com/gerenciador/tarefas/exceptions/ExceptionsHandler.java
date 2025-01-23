package com.gerenciador.tarefas.exceptions;

import com.gerenciador.tarefas.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(NotPermittedDeleteException.class)
    public ResponseEntity<ErrorResponse> notPermittedDeleteExceptionHandler(NotPermittedDeleteException ex) {

        Map<String, String> response = new HashMap<>();
        response.put("code", ErrorsEnum.NOT_PERMITTED_DELETE.toString());
        response.put("message", "It is not allowed to delete a task other than CREATED");

        ErrorResponse errorResponse =
                ErrorResponse.
                        builder()
                        .status(HttpStatus.UNPROCESSABLE_ENTITY.toString())
                        .errors(Collections.singletonList(response))
                        .build();


        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(NotPermittedUpdateTaskStatusException.class)
    public ResponseEntity<ErrorResponse> notPermittedUpdateTaskStatusExceptionHandler(NotPermittedUpdateTaskStatusException ex) {

        Map<String, String> response = new HashMap<>();
        response.put("code", ErrorsEnum.NOT_PERMITTED_MOVE_STATUS.toString());
        response.put("message", ex.getMessage());

        ErrorResponse errorResponse =
                ErrorResponse.
                        builder()
                        .status(HttpStatus.UNPROCESSABLE_ENTITY.toString())
                        .errors(Collections.singletonList(response))
                        .build();


        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(TaskExistingException.class)
    public ResponseEntity<ErrorResponse> TaskExistingExceptionHandler(TaskExistingException ex) {

        Map<String, String> response = new HashMap<>();
        response.put("code", ErrorsEnum.TASK_EXISTING.toString());
        response.put("message", ex.getMessage());

        ErrorResponse errorResponse =
                ErrorResponse.
                        builder()
                        .status(HttpStatus.UNPROCESSABLE_ENTITY.toString())
                        .errors(Collections.singletonList(response))
                        .build();


        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
