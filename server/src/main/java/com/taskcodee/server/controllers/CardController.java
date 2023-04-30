package com.taskcodee.server.controllers;

import com.taskcodee.server.api.ApiSuccess;
import com.taskcodee.server.dto.cards.CardCreationDTO;
import com.taskcodee.server.dto.cards.CardDTO;
import com.taskcodee.server.entities.BoardCard;
import com.taskcodee.server.mappers.CardMapper;
import com.taskcodee.server.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private CardMapper cardMapper;

    @GetMapping("/cards")
    public List<CardDTO> getCards() {
        return cardService.findAll().stream().map(cardMapper::mapToCardDTO).collect(Collectors.toList());
    }

    @GetMapping("/cards/{id}")
    public CardDTO getCardById(@PathVariable Long id) {
        return cardMapper.mapToCardDTO(cardService.findById(id));
    }

    @PostMapping("/cards")
    public ResponseEntity<Object> addCard(@RequestBody CardCreationDTO cardCreationDTO) {
        BoardCard boardCard = cardService.save(cardCreationDTO);
        ApiSuccess apiSuccess = new ApiSuccess("The card is created!", cardMapper.mapToCardDTO(boardCard));
        return new ResponseEntity<>(apiSuccess, HttpStatus.CREATED);
    }

    @PutMapping("/cards/{id}")
    public ResponseEntity<Object> putCard(@PathVariable Long id, @RequestBody CardCreationDTO cardCreationDTO) {
        BoardCard boardCard = cardService.update(id, cardCreationDTO);
        ApiSuccess apiSuccess = new ApiSuccess("The cardList is updated!", cardMapper.mapToCardDTO(boardCard));
        return new ResponseEntity<>(apiSuccess, HttpStatus.OK);
    }

//    @PatchMapping("/cards/{id}")
//    public ResponseEntity<Object> patchCard(@PathVariable Long id, @RequestBody CardCreationDTO cardCreationDTO) {
//        BoardCard boardCard = cardService.partialUpdate(id, cardCreationDTO);
//        ApiSuccess apiSuccess = new ApiSuccess("The cardList is updated!", boardCard);
//        return new ResponseEntity<>(apiSuccess, HttpStatus.OK);
//    }

    @DeleteMapping("/cards/{id}")
    public ResponseEntity<Object> removeCard(@PathVariable Long id) {
        cardService.deleteById(id);
        ApiSuccess apiSuccess = new ApiSuccess("The card is removed!");
        return new ResponseEntity<>(apiSuccess, HttpStatus.OK);
    }
}
