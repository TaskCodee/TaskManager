package com.taskcodee.server.dto;

public class CardListCreationDTO {

    private Long boardId;

    private String title;

    public CardListCreationDTO() {
    }

    public CardListCreationDTO(Long boardId, String title) {
        this.boardId = boardId;
        this.title = title;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
