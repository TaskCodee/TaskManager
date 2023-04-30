package com.taskcodee.server.dto.boardmembers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardMemberDTO {

    private Long userId;

    private Long boardId;

    private String role;

}
