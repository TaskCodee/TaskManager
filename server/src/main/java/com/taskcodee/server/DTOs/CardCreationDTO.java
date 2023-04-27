package com.taskcodee.server.DTOs;

public class CardCreationDTO {

    private Long cardListId;

    private String title;

    private String description;

    public CardCreationDTO() {
    }

    public CardCreationDTO(Long cardListId, String title, String description) {
        this.cardListId = cardListId;
        this.title = title;
        this.description = description;
    }

    public Long getCardListId() {
        return cardListId;
    }

    public void setCardListId(Long cardListId) {
        this.cardListId = cardListId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
