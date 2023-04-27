package com.taskcodee.server.services;

import com.taskcodee.server.dto.CardListCreationDTO;
import com.taskcodee.server.entities.BoardList;
import com.taskcodee.server.exceptions.MyEntityNotFoundException;
import com.taskcodee.server.repositoires.CardListRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardListService {

    @Autowired
    private CardListRepository cardListRepository;

    @Autowired
    private BoardService boardService;

    public List<BoardList> findAll() {
        return cardListRepository.findAll();
    }

    public BoardList getReferenceById(Long id) {
        return cardListRepository.getReferenceById(id);
    }

    public BoardList save(CardListCreationDTO cardListCreationDTO) {
        BoardList boardList = new BoardList();
        boardList.setTitle(cardListCreationDTO.getTitle());
        boardList.setBoard(boardService.getReferenceById(cardListCreationDTO.getBoardId()));
        try {
            boardList = cardListRepository.save(boardList);
        } catch (ConstraintViolationException ex) {
            throw new MyEntityNotFoundException(cardListCreationDTO.getBoardId());
        }
        return boardList;
    }
}
