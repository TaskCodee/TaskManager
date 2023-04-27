package com.taskcodee.server.services;

import com.taskcodee.server.dto.*;
import com.taskcodee.server.entities.Board;
import com.taskcodee.server.entities.BoardCard;
import com.taskcodee.server.entities.BoardList;
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

    public CardListDTO mapToCardListDTO(BoardList boardList) {
        CardListDTO cardListDTO = new CardListDTO();
        cardListDTO.setId(boardList.getId());
        cardListDTO.setTitle(boardList.getTitle());
        return cardListDTO;
    }

    public CardDTO mapToCardDTO(BoardCard boardCard) {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setId(boardCard.getId());
        cardDTO.setTitle(boardCard.getTitle());
        cardDTO.setDescription(boardCard.getDescription());
        return cardDTO;
    }

}
