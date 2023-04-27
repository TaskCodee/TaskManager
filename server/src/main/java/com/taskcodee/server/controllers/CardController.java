package com.taskcodee.server.controllers;

import com.taskcodee.server.APIs.ApiSuccess;
import com.taskcodee.server.DTOs.CardCreationDTO;
import com.taskcodee.server.DTOs.CardDTO;
import com.taskcodee.server.services.CardService;
import com.taskcodee.server.services.MappingUtils;
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
    private MappingUtils mappingUtils;

    @Autowired
    private CardService cardService;

    @GetMapping("/cards")
    public List<CardDTO> getCards() {
        return cardService.findAll().stream().map(mappingUtils::mapToCardDTO).collect(Collectors.toList());
    }

    @PostMapping("/cards")
    public ResponseEntity<Object> addCard(@RequestBody CardCreationDTO cardCreationDTO) {
        cardService.save(cardCreationDTO);
        ApiSuccess apiSuccess = new ApiSuccess("The card is created!");
        return new ResponseEntity<>(apiSuccess, HttpStatus.CREATED);
    }

}
