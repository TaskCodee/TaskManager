package com.taskcodee.server.services;

import com.taskcodee.server.dto.lists.BoardListCreationDTO;
import com.taskcodee.server.dto.lists.BoardListPutDTO;
import com.taskcodee.server.dto.lists.ListIndexDTO;
import com.taskcodee.server.entities.Board;
import com.taskcodee.server.entities.BoardCard;
import com.taskcodee.server.entities.BoardList;
import com.taskcodee.server.exceptions.MyEntityNotFoundException;
import com.taskcodee.server.repositoires.BoardListRepository;
import jakarta.transaction.Transactional;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Double maxCardPositionFromBoardListById(Long id) {
        Optional<Double> res = boardListRepository.maxCardPositionFromBoardListById(id);
        return res.orElse(0.0);
    }

    @Transactional
    public BoardList save(Long boardId, BoardListCreationDTO boardListCreationDTO) {
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

    @Transactional
    public BoardList changeIndex(Long listId, ListIndexDTO listIndexDTO) {
        BoardList boardList = this.findById(listId);
        Board board = boardList.getBoard();
        if(listIndexDTO.getIndexList() == null) {
            throw new IndexOutOfBoundsException(listIndexDTO.getIndexList());
        }
        if(listIndexDTO.getIndexList() < 0 || listIndexDTO.getIndexList() > board.getLists().size()) {
            throw new IndexOutOfBoundsException(listIndexDTO.getIndexList());
        }

        if(listIndexDTO.getIndexList() == 0) {
            boardList.setPos(board.getLists().get(0).getPos() / 2);
        }
        else if(listIndexDTO.getIndexList() == board.getLists().size() - 1) {
            boardList.setPos(board.getLists().get(board.getLists().size() - 1).getPos() + 100);
        }
        else {
            if(listIndexDTO.getIndexList() < board.getLists().indexOf(boardList)) {
                boardList.setPos((board.getLists().get(listIndexDTO.getIndexList() - 1).getPos() +
                        board.getLists().get(listIndexDTO.getIndexList()).getPos()) / 2);
            }
            else {
                boardList.setPos((board.getLists().get(listIndexDTO.getIndexList()).getPos() +
                        board.getLists().get(listIndexDTO.getIndexList() + 1).getPos()) / 2);
            }
        }
        return boardListRepository.save(boardList);
    }

    public void deleteById(Long id) {
        boardListRepository.deleteById(id);
    }
}
