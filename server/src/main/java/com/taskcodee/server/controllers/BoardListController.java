package com.taskcodee.server.controllers;

import com.taskcodee.server.api.ApiSuccess;
import com.taskcodee.server.dto.cards.CardDTO;
import com.taskcodee.server.dto.lists.BoardListCreationDTO;
import com.taskcodee.server.dto.lists.BoardListDTO;
import com.taskcodee.server.dto.lists.BoardListPutDTO;
import com.taskcodee.server.entities.BoardList;
import com.taskcodee.server.mappers.BoardListMapper;
import com.taskcodee.server.services.BoardListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BoardListController {

    @Autowired
    private BoardListService boardListService;

    @Autowired
    private BoardListMapper boardListMapper;

    @GetMapping("/lists")
    public List<BoardListDTO> getCardLists() {
        return boardListService.findAll().stream().map(boardListMapper::mapToBoardListDTO).collect(Collectors.toList());
    }

    @GetMapping("/lists/{id}")
    public BoardListDTO getCardListById(@PathVariable Long id) {
        return boardListMapper.mapToBoardListDTO(boardListService.findById(id));
    }

    @PostMapping("/lists")
    public ResponseEntity<Object> addCardList(@RequestBody BoardListCreationDTO boardListCreationDTO) {
        boardListService.save(boardListCreationDTO);
        ApiSuccess apiSuccess = new ApiSuccess("The cardList is created!");
        return new ResponseEntity<>(apiSuccess, HttpStatus.CREATED);
    }

    @PutMapping("/lists/{id}")
    public ResponseEntity<Object> putCardList(@PathVariable Long id, @RequestBody BoardListPutDTO boardListPutDTO) {
        BoardList boardList = boardListService.update(id, boardListPutDTO);
        ApiSuccess apiSuccess = new ApiSuccess("The cardList is updated!", boardListMapper.mapToBoardListDTO(boardList));
        return new ResponseEntity<>(apiSuccess, HttpStatus.OK);
    }

    @DeleteMapping("/lists/{id}")
    public ResponseEntity<Object> removeCardList(@PathVariable Long id) {
        boardListService.deleteById(id);
        ApiSuccess apiSuccess = new ApiSuccess("The cardList is removed!");
        return new ResponseEntity<>(apiSuccess, HttpStatus.OK);
    }
}
