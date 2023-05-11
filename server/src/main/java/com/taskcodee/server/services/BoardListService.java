package com.taskcodee.server.services;

import com.taskcodee.server.dto.lists.BoardListCreationDTO;
import com.taskcodee.server.dto.lists.BoardListPutDTO;
import com.taskcodee.server.entities.BoardList;
import com.taskcodee.server.exceptions.MyEntityNotFoundException;
import com.taskcodee.server.repositoires.BoardListRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardListService {

    @Autowired
    private BoardListRepository boardListRepository;

    @Autowired
    private BoardService boardService;

    public List<BoardList> findAll() {
        return boardListRepository.findAll();
    }

    public BoardList findById(Long id) {
        return boardListRepository.findById(id).orElseThrow(() -> new MyEntityNotFoundException(id));
    }

    public BoardList getReferenceById(Long id) {
        return boardListRepository.getReferenceById(id);
    }

    public BoardList save(Long boardId, BoardListCreationDTO boardListCreationDTO) {
        BoardList boardList = new BoardList();
        boardList.setTitle(boardListCreationDTO.getTitle());
        boardList.setBoard(boardService.getReferenceById(boardId));
        try {
            boardList = boardListRepository.save(boardList);
        } catch (ConstraintViolationException ex) {
            throw new MyEntityNotFoundException(boardId);
        }
        return boardList;
    }

    public BoardList update(Long id, BoardListPutDTO boardListPutDTO) {
        BoardList boardList = findById(id);
        boardList.setTitle(boardListPutDTO.getTitle());
        return boardListRepository.save(boardList);
    }

    public void deleteById(Long id) {
        boardListRepository.deleteById(id);
    }
}
