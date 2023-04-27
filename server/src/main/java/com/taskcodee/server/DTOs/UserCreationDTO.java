package com.taskcodee.server.DTOs;

import jakarta.validation.constraints.Size;

public class UserCreationDTO {

    @Size(min=2, max=50)
    private String username;

    public UserCreationDTO() {
    }

    public UserCreationDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
