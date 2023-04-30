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
@Table(name = "boards")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    @JsonView({View.BoardBasic.class})
    private List<BoardMember> members;

}
