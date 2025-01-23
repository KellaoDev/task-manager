package com.gerenciador.tarefas.validation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gerenciador.tarefas.response.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ValidationFieldHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> processValidations(MethodArgumentNotValidException ex) {
        List<Map<String, String>> listErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> {
                    Map<String, String> errors = new HashMap<>();
                    errors.put("field", getNameProperties(erro));
                    errors.put("description", erro.getDefaultMessage());

                    return errors;
                })
                .toList();

        ErrorResponse response = ErrorResponse
                .builder()
                .status(HttpStatus.BAD_REQUEST.toString())
                .errors(listErrors)
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private String getNameProperties(final FieldError error) {
        if (error.contains(ConstraintViolation.class)) {

            try {
                final ConstraintViolation<?> violation = error.unwrap(ConstraintViolation.class);
                final Field field = violation.getRootBeanClass().getDeclaredField(error.getField());
                final JsonProperty annotation = field.getAnnotation(JsonProperty.class);

                if (annotation != null && annotation.value() != null && !annotation.value().isEmpty()) {
                    return annotation.value();
                }
            } catch (Exception e) {
            }
        }
        return error.getField();
    }
}
