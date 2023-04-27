package com.taskcodee.server.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class MyEntityNotFoundException extends EntityNotFoundException {

    public MyEntityNotFoundException() {
        super("Entity is not found");
    }

    public MyEntityNotFoundException(Long id) {
        super("Entity not found, id="+id);
    }
}
