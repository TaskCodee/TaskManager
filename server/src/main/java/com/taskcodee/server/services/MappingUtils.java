package com.taskcodee.server.services;

import com.taskcodee.server.DTOs.*;
import com.taskcodee.server.entities.Board;
import com.taskcodee.server.entities.Card;
import com.taskcodee.server.entities.CardList;
import com.taskcodee.server.entities.User;
import org.springframework.stereotype.Service;

@Service
public class MappingUtils {

    public User mapToUserEntity(UserCreationDTO userCreationDTO) {
        User user = new User();
        user.setUsername(userCreationDTO.getUsername());
        return user;
    }

    public Board mapToBoardEntity(BoardCreationDTO boardCreationDTO) {
        Board board = new Board();
        board.setTitle(boardCreationDTO.getTitle());
        return board;
    }

    public BoardDTO mapToBoardDTO(Board board) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(board.getId());
        boardDTO.setTitle(board.getTitle());
        return boardDTO;
    }

    public CardListDTO mapToCardListDTO(CardList cardList) {
        CardListDTO cardListDTO = new CardListDTO();
        cardListDTO.setId(cardList.getId());
        cardListDTO.setTitle(cardList.getTitle());
        return cardListDTO;
    }

    public CardDTO mapToCardDTO(Card card) {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setId(card.getId());
        cardDTO.setTitle(card.getTitle());
        cardDTO.setDescription(card.getDescription());
        return cardDTO;
    }

}
