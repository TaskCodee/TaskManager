package com.taskcodee.server.dto;

import java.util.List;

public class BoardCardListCardDTO {

    private Long id;

    private String title;

    private List<CardListCardDTO> cardListCardDTOlist;

    public BoardCardListCardDTO() {
    }

    public BoardCardListCardDTO(Long id, String title, List<CardListCardDTO> cardListCardDTOlist) {
        this.id = id;
        this.title = title;
        this.cardListCardDTOlist = cardListCardDTOlist;
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

    public List<CardListCardDTO> getCardListCardDTOlist() {
        return cardListCardDTOlist;
    }

    public void setCardListCardDTOlist(List<CardListCardDTO> cardListCardDTOlist) {
        this.cardListCardDTOlist = cardListCardDTOlist;
    }
}
