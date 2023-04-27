package com.taskcodee.server.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.taskcodee.server.views.View;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({View.BoardBasic.class})
    private Long id;

    //@Size(min=2, max=50)
    @Column(unique=true, nullable = false)
    private String username;

    @ManyToMany
    @JoinTable(name = "user_card",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private List<BoardCard> boards;

    @OneToMany(mappedBy = "user")
    private List<BoardMember> boardMemberList;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<BoardCard> getBoards() {
        return boards;
    }

    public void setBoards(List<BoardCard> boards) {
        this.boards = boards;
    }

    public List<BoardMember> getBoardMemberList() {
        return boardMemberList;
    }

    public void setBoardMemberList(List<BoardMember> boardMemberList) {
        this.boardMemberList = boardMemberList;
    }
}
