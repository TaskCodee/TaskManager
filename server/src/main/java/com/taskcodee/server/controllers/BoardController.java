package com.taskcodee.server.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.taskcodee.server.api.ApiSuccess;
import com.taskcodee.server.dto.*;
import com.taskcodee.server.entities.Board;
import com.taskcodee.server.services.BoardService;
import com.taskcodee.server.services.MappingUtils;
import com.taskcodee.server.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/boards-old/{id}")
    public BoardDTO getBoardById(@PathVariable Long id) {
        return mappingUtils.mapToBoardDTO(boardService.findById(id));
    }

    @GetMapping("/boards/{id}")
    @JsonView({View.BoardBasic.class})
    public Board getBoardByIdNew(@PathVariable Long id) {
/*        Board board = boardService.findById(id);
        BoardCardListCardDTO boardCardListCardDTO = new BoardCardListCardDTO();
        boardCardListCardDTO.setId(board.getId());
        boardCardListCardDTO.setTitle(board.getTitle());
        boardCardListCardDTO.setCardListCardDTOlist(board.getLists().stream()
                .map(obj1 -> new CardListCardDTO(obj1.getId(), obj1.getTitle(),
                        obj1.getCards().stream()
                                .map(obj2 -> new CardDTO(obj2.getId(), obj2.getTitle(), obj2.getDescription()))
                                .collect(toList())))
                .collect(toList()));
        return boardCardListCardDTO;*/
        Board board = boardService.findById(id);
        System.out.println("ftgyhuio");
        return board;
    }

    @PostMapping("/boards")
    public ResponseEntity<Object> createBoardForOwner(@RequestBody BoardCreationDTO boardCreationDTO) {
        boardService.createBoardForOwner(boardCreationDTO);
        ApiSuccess apiSuccess = new ApiSuccess("The board is created!");
        return new ResponseEntity<>(apiSuccess, HttpStatus.CREATED);
    }

}
