package com.taskcodee.server.services;

import com.taskcodee.server.dto.cards.CardCreationDTO;
import com.taskcodee.server.dto.cards.CardUpdateDTO;
import com.taskcodee.server.dto.cards.IndexDTO;
import com.taskcodee.server.entities.BoardCard;
import com.taskcodee.server.entities.BoardList;
import com.taskcodee.server.exceptions.MyEntityNotFoundException;
import com.taskcodee.server.mappers.CardMapper;
import com.taskcodee.server.repositoires.CardRepository;
import jakarta.transaction.Transactional;
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

    public BoardCard save(Long cardListId, CardCreationDTO cardCreationDTO) {
        BoardCard card = new BoardCard();
        card.setTitle(cardCreationDTO.getTitle());
        card.setDescription(cardCreationDTO.getDescription());
        card.setList(boardListService.getReferenceById(cardListId));
        try {
            card = cardRepository.save(card);
        } catch (ConstraintViolationException ex) {
            throw new MyEntityNotFoundException(cardListId);
        }
        return card;
    }

    public BoardCard update(Long id, CardUpdateDTO cardUpdateDTO) {
        BoardCard boardCard = findById(id);
        if(cardUpdateDTO.getTitle() != null) {
            boardCard.setTitle(cardUpdateDTO.getTitle());
        }
        if(cardUpdateDTO.getDescription() != null) {
            boardCard.setDescription(cardUpdateDTO.getDescription());
        }
        if(cardUpdateDTO.getCardListId() != null) {
            boardCard.setList(boardListService.getReferenceById(cardUpdateDTO.getCardListId()));
        }
        return cardRepository.save(boardCard);
    }

    @Transactional
    public void changeIndex(Long cardId, IndexDTO indexDTO) {
        BoardCard boardCard = this.findById(cardId);
        BoardList boardList = boardListService.findById(boardCard.getList().getId());
        if(indexDTO.getIndex() < 0 || indexDTO.getIndex() > boardList.getCards().size()) {
            throw new IndexOutOfBoundsException(indexDTO.getIndex());
        }

        if(indexDTO.getIndex() == 0) {
            boardCard.setPos(boardList.getCards().get(0).getPos() / 2);
        }
        else if(indexDTO.getIndex() == boardList.getCards().size() - 1) {
            boardCard.setPos(boardList.getCards().get(boardList.getCards().size() - 1).getPos() + 100);
        }
        else {
            boardCard.setPos((boardList.getCards().get(indexDTO.getIndex()).getPos() +
                    boardList.getCards().get(indexDTO.getIndex() + 1).getPos()) / 2);
        }
        cardRepository.save(boardCard);
    }

    public void deleteById(Long id) {
        cardRepository.deleteById(id);
    }

}