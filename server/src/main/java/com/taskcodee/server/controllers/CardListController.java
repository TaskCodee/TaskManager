package com.taskcodee.server.controllers;

import com.taskcodee.server.api.ApiSuccess;
import com.taskcodee.server.dto.CardListCreationDTO;
import com.taskcodee.server.dto.CardListDTO;
import com.taskcodee.server.services.CardListService;
import com.taskcodee.server.services.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CardListController {

    @Autowired
    private CardListService cardListService;

    @Autowired
    private MappingUtils mappingUtils;

    @GetMapping("/lists")
    public List<CardListDTO> getCardLists() {
        return cardListService.findAll().stream().map(mappingUtils::mapToCardListDTO).collect(Collectors.toList());
    }

    @PostMapping("/lists")
    public ResponseEntity<Object> addCardList(@RequestBody CardListCreationDTO cardListCreationDTO) {
        cardListService.save(cardListCreationDTO);
        ApiSuccess apiSuccess = new ApiSuccess("The cardList is created!");
        return new ResponseEntity<>(apiSuccess, HttpStatus.CREATED);
    }
}
