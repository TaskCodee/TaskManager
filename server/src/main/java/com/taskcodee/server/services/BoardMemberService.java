package com.taskcodee.server.services;

import com.taskcodee.server.DTOs.BoardCreationDTO;
import com.taskcodee.server.DTOs.BoardMemberDTO;
import com.taskcodee.server.DTOs.UserCreationDTO;
import com.taskcodee.server.entities.Board;
import com.taskcodee.server.entities.BoardMember;
import com.taskcodee.server.entities.User;
import com.taskcodee.server.repositoires.BoardMemberRepository;
import com.taskcodee.server.repositoires.BoardRepository;
import com.taskcodee.server.repositoires.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardMemberService {

    @Autowired
    private BoardMemberRepository boardMemberRepository;

//    @Autowired
//    private BoardService boardService;

    @Autowired
    private UserService userService;

//    public void createBoardForOwner(User user, Board board) {
//        BoardMember boardMember = new BoardMember();
//        boardMember.setUser(userRepository.getReferenceById(user.getId()));
//        boardMember.setBoard(boardRepository.getReferenceById(board.getId()));
//    }

//    public void addUserToBoard(BoardMemberDTO boardMemberDTO) {
//        BoardMember boardMember = new BoardMember();
//        boardMember.setUser(userService.getReferenceById(boardMemberDTO.getUserId()));
//        boardMember.setBoard(boardService.getReferenceById(boardMemberDTO.getBoardId()));
//        boardMemberRepository.save(boardMember);
//    }

    public BoardMember save(BoardMember boardMember) {
        return boardMemberRepository.save(boardMember);
    }
}
