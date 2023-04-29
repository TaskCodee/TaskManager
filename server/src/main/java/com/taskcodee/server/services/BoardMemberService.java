package com.taskcodee.server.services;

import com.taskcodee.server.entities.BoardMember;
import com.taskcodee.server.repositoires.BoardMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardMemberService {

    @Autowired
    private BoardMemberRepository boardMemberRepository;

    @Autowired
    private UserService userService;

    public BoardMember save(BoardMember boardMember) {
        return boardMemberRepository.save(boardMember);
    }
}
