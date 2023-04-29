package com.taskcodee.server.services;

import com.taskcodee.server.dto.cards.CardCreationDTO;
import com.taskcodee.server.dto.cards.CardDTO;
import com.taskcodee.server.dto.lists.BoardListPutDTO;
import com.taskcodee.server.entities.BoardCard;
import com.taskcodee.server.entities.BoardList;
import com.taskcodee.server.exceptions.MyEntityNotFoundException;
import com.taskcodee.server.mappers.CardMapper;
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
    private BoardListService boardListService;

    @Autowired
    private CardMapper cardMapper;

    public List<BoardCard> findAll() {
        return cardRepository.findAll();
    }

    public BoardCard findById(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> new MyEntityNotFoundException(id));
    }

    public BoardCard getReferenceById(Long id) {
        return cardRepository.getReferenceById(id);
    }

    public BoardCard save(CardCreationDTO cardCreationDTO) {
        BoardCard card = new BoardCard();
        card.setTitle(cardCreationDTO.getTitle());
        card.setDescription(cardCreationDTO.getDescription());
        card.setList(boardListService.getReferenceById(cardCreationDTO.getCardListId()));
        try {
            card = cardRepository.save(card);
        } catch (ConstraintViolationException ex) {
            throw new MyEntityNotFoundException(cardCreationDTO.getCardListId());
        }
        return card;
    }

    public BoardCard update(Long id, CardCreationDTO cardCreationDTO) {
        BoardCard boardCard = findById(id);
        boardCard.setTitle(cardCreationDTO.getTitle());
        boardCard.setDescription(cardCreationDTO.getDescription());
        boardCard.setList(boardListService.getReferenceById(cardCreationDTO.getCardListId()));
        return cardRepository.save(boardCard);
    }

//    public BoardCard partialUpdate(Long id, CardCreationDTO cardCreationDTO) {
//        CardDTO cardDTO = cardMapper.mapToCardDTO(findById(id));
//        if(cardCreationDTO.getCardListId() != null) {
//            cardDTO.setId(id);
//        }
//        if(!cardCreationDTO.getTitle().equals(null)) {
//            cardDTO.setTitle(cardCreationDTO.getTitle());
//        }
//        if(!cardCreationDTO.getDescription().equals(null)) {
//            cardDTO.setDescription(cardCreationDTO.getDescription());
//        }
//        return cardRepository.save(boardCard);
//    }

    public void deleteById(Long id) {
        cardRepository.deleteById(id);
    }

}
