package com.taskcodee.server.exceptions;

public class EntityAlreadyExistsException extends RuntimeException {

    public EntityAlreadyExistsException(String messageError) {
        super(messageError);
    }
}
