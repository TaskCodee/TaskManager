package com.taskcodee.server.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.taskcodee.server.views.View;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "lists")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({View.BoardBasic.class, View.CardBasic.class })
    private Long id;

    @Size(min=2, max=50)
    @JsonView({View.BoardBasic.class, View.CardBasic.class })
    private String title;

    @ManyToOne
    private Board board;

    @OneToMany(mappedBy = "list", cascade = CascadeType.REMOVE)
    @JsonView({View.BoardBasic.class})
    private List<BoardCard> cards;

}
