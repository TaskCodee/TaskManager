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
@Table(name = "cards")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ View.BoardBasic.class, View.CardBasic.class })
    private Long id;

    @Size(min = 2, max = 50)
    @JsonView({ View.BoardBasic.class, View.CardBasic.class })
    private String title;

    @JsonView({ View.BoardBasic.class, View.CardBasic.class })
    private String description;

    @JsonView({View.BoardBasic.class, View.CardBasic.class })
    private Double pos;

    @ManyToOne
    @JsonView({ View.CardBasic.class })
    private BoardList list;

    @ManyToMany(mappedBy = "boards", cascade = CascadeType.REMOVE)
    private List<BoardMember> boardMembers;

}
