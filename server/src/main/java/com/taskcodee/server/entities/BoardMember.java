package com.taskcodee.server.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.taskcodee.server.views.View;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "board_members")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView({View.BoardBasic.class})
    private String role;

    @ManyToOne
    private Board board;

    @ManyToOne
    @JsonView({View.BoardBasic.class})
    private User user;

    @ManyToMany
    @JoinTable(name = "card_members",
            joinColumns = @JoinColumn(name = "board_member_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private List<BoardCard> boards;

}
