package com.taskcodee.server.dto;

public class BoardCreationDTO {

    private Long userId;

    private String title;

    public BoardCreationDTO() {
    }

    public BoardCreationDTO(Long userId, String title) {
        this.userId = userId;
        this.title = title;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
