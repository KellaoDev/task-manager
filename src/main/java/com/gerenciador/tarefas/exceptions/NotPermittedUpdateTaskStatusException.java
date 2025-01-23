package com.gerenciador.tarefas.exceptions;

public class NotPermittedUpdateTaskStatusException extends RuntimeException{

    public NotPermittedUpdateTaskStatusException() {
        super();
    }

    public NotPermittedUpdateTaskStatusException(String message) {
        super(message);
    }

}
