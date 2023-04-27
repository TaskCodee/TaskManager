package com.taskcodee.server.services;

import com.taskcodee.server.DTOs.CardListCreationDTO;
import com.taskcodee.server.entities.CardList;
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

    public List<CardList> findAll() {
        return cardListRepository.findAll();
    }

    public CardList getReferenceById(Long id) {
        return cardListRepository.getReferenceById(id);
    }

    public CardList save(CardListCreationDTO cardListCreationDTO) {
        CardList cardList = new CardList();
        cardList.setTitle(cardListCreationDTO.getTitle());
        cardList.setBoard(boardService.getReferenceById(cardListCreationDTO.getBoardId()));
        try {
            cardList = cardListRepository.save(cardList);
        } catch (ConstraintViolationException ex) {
            throw new MyEntityNotFoundException(cardListCreationDTO.getBoardId());
        }
        return cardList;
    }
}
