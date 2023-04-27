package com.taskcodee.server.DTOs;

import java.util.List;

public class CardListCardDTO {

    private Long id;

    private String title;

    private List<CardDTO> cardDTOList;

    public CardListCardDTO() {
    }

    public CardListCardDTO(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public CardListCardDTO(Long id, String title, List<CardDTO> cardDTOList) {
        this.id = id;
        this.title = title;
        this.cardDTOList = cardDTOList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CardDTO> getCardDTOList() {
        return cardDTOList;
    }

    public void setCardDTOList(List<CardDTO> cardDTOList) {
        this.cardDTOList = cardDTOList;
    }
}
