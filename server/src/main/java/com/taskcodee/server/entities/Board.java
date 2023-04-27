package com.taskcodee.server.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=2, max=50)
    private String title;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<CardList> cardListList;

    @OneToMany(mappedBy = "board")
    private List<BoardMember> boardMemberList;

    public Board() {
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

    public List<CardList> getCardListList() {
        return cardListList;
    }

    public void setCardListList(List<CardList> cardListList) {
        this.cardListList = cardListList;
    }

    public List<BoardMember> getBoardMemberList() {
        return boardMemberList;
    }

    public void setBoardMemberList(List<BoardMember> boardMemberList) {
        this.boardMemberList = boardMemberList;
    }
}
