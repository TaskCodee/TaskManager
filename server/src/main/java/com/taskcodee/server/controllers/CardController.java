package com.taskcodee.server.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.taskcodee.server.api.ApiSuccess;
import com.taskcodee.server.dto.cards.CardCreationDTO;
import com.taskcodee.server.dto.cards.CardUpdateDTO;
import com.taskcodee.server.dto.cards.IndexDTO;
import com.taskcodee.server.entities.BoardCard;
import com.taskcodee.server.mappers.CardMapper;
import com.taskcodee.server.services.CardService;
import com.taskcodee.server.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private CardMapper cardMapper;

    @GetMapping("/cards")
    @JsonView({View.CardBasic.class})
    public List<BoardCard> getCards() {
        return cardService.findAll();
    }

    @GetMapping("/cards/{id}")
    @JsonView({View.CardBasic.class})
    public BoardCard getCardById(@PathVariable Long id) {
        return cardService.findById(id);
    }

    @PostMapping("/lists/{id}/cards")
    public ResponseEntity<Object> addCard(@PathVariable Long id, @RequestBody CardCreationDTO cardCreationDTO) {
        BoardCard boardCard = cardService.save(id, cardCreationDTO);
        ApiSuccess apiSuccess = new ApiSuccess("The card is created!", cardMapper.mapToCardDTO(boardCard));
        return new ResponseEntity<>(apiSuccess, HttpStatus.CREATED);
    }

    @PatchMapping("/cards/{id}/move_to")
    public ResponseEntity<Object> changeOrder(@PathVariable Long id, @RequestBody IndexDTO indexDTO) {
        BoardCard boardCard = cardService.changeIndex(id, indexDTO);
        ApiSuccess apiSuccess = new ApiSuccess("The position is changed!", cardMapper.mapToCardDTO(boardCard));
        return new ResponseEntity<>(apiSuccess, HttpStatus.CREATED);
    }

    @PatchMapping("/cards/{id}")
    public ResponseEntity<Object> putCard(@PathVariable Long id, @RequestBody CardUpdateDTO cardUpdateDTO) {
        BoardCard boardCard = cardService.update(id, cardUpdateDTO);
        ApiSuccess apiSuccess = new ApiSuccess("The cardList is updated!", cardMapper.mapToCardDTO(boardCard));
        return new ResponseEntity<>(apiSuccess, HttpStatus.OK);
    }

    @DeleteMapping("/cards/{id}")
    public ResponseEntity<Object> removeCard(@PathVariable Long id) {
        cardService.deleteById(id);
        ApiSuccess apiSuccess = new ApiSuccess("The card is removed!");
        return new ResponseEntity<>(apiSuccess, HttpStatus.OK);
    }
}
