package com.taskcodee.server.services;

import com.taskcodee.server.DTOs.BoardCreationDTO;
import com.taskcodee.server.entities.Board;
import com.taskcodee.server.entities.BoardMember;
import com.taskcodee.server.exceptions.EntityAlreadyExistsException;
import com.taskcodee.server.exceptions.MyEntityNotFoundException;
import com.taskcodee.server.repositoires.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private MappingUtils mappingUtils;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BoardMemberService boardMemberService;

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public Board findById(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new MyEntityNotFoundException(id));
    }

    public Board getReferenceById(Long id) {
        return boardRepository.getReferenceById(id);
    }

    @Transactional
    public void createBoardForOwner(BoardCreationDTO boardCreationDTO) {
        Long count = boardRepository.countBoards(boardCreationDTO.getTitle(), boardCreationDTO.getUserId());
        if(count != 0) {
            throw new EntityAlreadyExistsException("This table already exists");
        }
        Board board = boardRepository.save(mappingUtils.mapToBoardEntity(boardCreationDTO));
        BoardMember boardMember = new BoardMember();
        boardMember.setUser(userService.getReferenceById(boardCreationDTO.getUserId()));
        boardMember.setBoard(boardRepository.getReferenceById(board.getId()));
        boardMember.setRole("Owner");
        boardMemberService.save(boardMember);
    }
}
