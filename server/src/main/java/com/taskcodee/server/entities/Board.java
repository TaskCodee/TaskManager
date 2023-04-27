package com.taskcodee.server.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.taskcodee.server.views.View;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({View.BoardBasic.class})
    private Long id;

    @Size(min=2, max=50)
    @JsonView({View.BoardBasic.class})
    private String title;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    @JsonView({View.BoardBasic.class})
    private List<BoardList> lists;

    @OneToMany(mappedBy = "board")
    @JsonView({View.BoardBasic.class})
    private List<BoardMember> members;

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

    public List<BoardList> getLists() {
        return lists;
    }

    public void setLists(List<BoardList> lists) {
        this.lists = lists;
    }

    public List<BoardMember> getMembers() {
        return members;
    }

    public void setMembers(List<BoardMember> members) {
        this.members = members;
    }
}
