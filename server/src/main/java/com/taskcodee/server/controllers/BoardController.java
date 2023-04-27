package com.taskcodee.server.controllers;

import com.taskcodee.server.APIs.ApiSuccess;
import com.taskcodee.server.DTOs.*;
import com.taskcodee.server.entities.Board;
import com.taskcodee.server.entities.Card;
import com.taskcodee.server.services.BoardService;
import com.taskcodee.server.services.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private MappingUtils mappingUtils;

    @GetMapping("/boards")
    public List<BoardDTO> getBoards() {
        return boardService.findAll().stream().map(mappingUtils::mapToBoardDTO).collect(toList());
    }

    @GetMapping("/boards/{id}")
    public BoardDTO getBoardById(@PathVariable Long id) {
        return mappingUtils.mapToBoardDTO(boardService.findById(id));
    }

    @GetMapping("/newBoards/{id}")
    public BoardCardListCardDTO getBoardByIdNew(@PathVariable Long id) {
        Board board = boardService.findById(id);
        BoardCardListCardDTO boardCardListCardDTO = new BoardCardListCardDTO();
        boardCardListCardDTO.setId(board.getId());
        boardCardListCardDTO.setTitle(board.getTitle());
        boardCardListCardDTO.setCardListCardDTOlist(board.getCardListList().stream().map(obj1 ->
                new CardListCardDTO(obj1.getId(), obj1.getTitle(), obj1.getCardList().stream().map(obj2 ->
                        new CardDTO(obj2.getId(), obj2.getTitle(), obj2.getDescription())).collect(toList()))).collect(toList()));
        return boardCardListCardDTO;
    }

    @PostMapping("/board")
    public ResponseEntity<Object> createBoardForOwner(@RequestBody BoardCreationDTO boardCreationDTO) {
        boardService.createBoardForOwner(boardCreationDTO);
        ApiSuccess apiSuccess = new ApiSuccess("The board is created!");
        return new ResponseEntity<>(apiSuccess, HttpStatus.CREATED);
    }

}
