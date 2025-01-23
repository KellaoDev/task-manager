package com.gerenciador.tarefas.exceptions;

public class TaskExistingException extends RuntimeException{

    public TaskExistingException () {
        super();
    }

    public TaskExistingException(String message) {
        super(message);
    }

}
