package com.taskcodee.server.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.taskcodee.server.views.View;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "cardList")
public class BoardList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({View.BoardBasic.class})
    private Long id;

    @Size(min=2, max=50)
    @JsonView({View.BoardBasic.class})
    private String title;

    @ManyToOne
    private Board board;

    @OneToMany(mappedBy = "list", cascade = CascadeType.REMOVE)
    @JsonView({View.BoardBasic.class})
    private List<BoardCard> cards;

    public BoardList() {
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

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<BoardCard> getCards() {
        return cards;
    }

    public void setCards(List<BoardCard> cards) {
        this.cards = cards;
    }
}
