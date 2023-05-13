package com.taskcodee.server.services;

import com.taskcodee.server.dto.lists.BoardListCreationDTO;
import com.taskcodee.server.dto.lists.BoardListPutDTO;
import com.taskcodee.server.entities.BoardList;
import com.taskcodee.server.exceptions.MyEntityNotFoundException;
import com.taskcodee.server.repositoires.BoardListRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public BoardList save(Long boardId, BoardListCreationDTO boardListCreationDTO) {
        //Обработать ситуацию, если у доски вообще не будет листов
        Double maxPosition = boardService.maxListPositionFromBoardById(boardId);

        BoardList boardList = new BoardList();
        boardList.setTitle(boardListCreationDTO.getTitle());
        boardList.setPos(maxPosition + 100);
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
