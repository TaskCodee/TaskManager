package com.taskcodee.server.services;

import com.taskcodee.server.DTOs.CardCreationDTO;
import com.taskcodee.server.entities.Board;
import com.taskcodee.server.entities.Card;
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

    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    public Card getReferenceById(Long id) {
        return cardRepository.getReferenceById(id);
    }

    public Card save(CardCreationDTO cardCreationDTO) {
        Card card = new Card();
        card.setTitle(cardCreationDTO.getTitle());
        card.setDescription(cardCreationDTO.getDescription());
        card.setListCard(cardListService.getReferenceById(cardCreationDTO.getCardListId()));
        try {
            card = cardRepository.save(card);
        } catch (ConstraintViolationException ex) {
            throw new MyEntityNotFoundException(cardCreationDTO.getCardListId());
        }
        return card;
    }

}
