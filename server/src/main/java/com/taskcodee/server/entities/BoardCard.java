package com.taskcodee.server.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.taskcodee.server.views.View;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "cards")
public class BoardCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({View.BoardBasic.class})
    private Long id;

    @Size(min=2, max=50)
    @JsonView({View.BoardBasic.class})
    private String title;

    @JsonView({View.BoardBasic.class})
    private String description;

    @ManyToOne
    private BoardList list;

    @ManyToMany(mappedBy = "boards")
    private List<User> users;

    public BoardCard() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BoardList getList() {
        return list;
    }

    public void setList(BoardList list) {
        this.list = list;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
