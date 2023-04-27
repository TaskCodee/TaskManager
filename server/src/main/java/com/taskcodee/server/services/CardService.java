package com.taskcodee.server.services;

import com.taskcodee.server.dto.CardCreationDTO;
import com.taskcodee.server.entities.BoardCard;
import com.taskcodee.server.exceptions.MyEntityNotFoundException;
import com.taskcodee.server.repositoires.CardRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardListService cardListService;

    public List<BoardCard> findAll() {
        return cardRepository.findAll();
    }

    public BoardCard getReferenceById(Long id) {
        return cardRepository.getReferenceById(id);
    }

    public BoardCard save(CardCreationDTO cardCreationDTO) {
        BoardCard card = new BoardCard();
        card.setTitle(cardCreationDTO.getTitle());
        card.setDescription(cardCreationDTO.getDescription());
        card.setList(cardListService.getReferenceById(cardCreationDTO.getCardListId()));
        try {
            card = cardRepository.save(card);
        } catch (ConstraintViolationException ex) {
            throw new MyEntityNotFoundException(cardCreationDTO.getCardListId());
        }
        return card;
    }

}
