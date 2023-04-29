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
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({View.BoardBasic.class})
    private Long id;

    //@Size(min=2, max=50)
    @Column(unique=true, nullable = false)
    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<BoardMember> boardMemberList;

}
