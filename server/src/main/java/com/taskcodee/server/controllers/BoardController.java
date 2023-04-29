package com.taskcodee.server.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.taskcodee.server.api.ApiSuccess;
import com.taskcodee.server.dto.boards.BoardCreationDTO;
import com.taskcodee.server.dto.boards.BoardDTO;
import com.taskcodee.server.entities.Board;
import com.taskcodee.server.mappers.BoardMapper;
import com.taskcodee.server.services.BoardService;
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
    private BoardMapper boardMapper;

    @GetMapping("/boards")
    public List<BoardDTO> getBoards() {
        return boardService.findAll().stream().map(boardMapper::mapToBoardDTO).collect(toList());
    }

    @GetMapping("/boards-old/{id}")
    public BoardDTO getBoardById(@PathVariable Long id) {
        return boardMapper.mapToBoardDTO(boardService.findById(id));
        //return mappingUtils.mapToBoardDTO(boardService.findById(id));
    }

    @GetMapping("/boards/{id}")
    @JsonView({View.BoardBasic.class})
    public Board getBoardByIdNew(@PathVariable Long id) {
        return boardService.findById(id);
    }

    @PostMapping("/boards")
    public ResponseEntity<Object> createBoardForOwner(@RequestBody BoardCreationDTO boardCreationDTO) {
        boardService.createBoardForOwner(boardCreationDTO);
        ApiSuccess apiSuccess = new ApiSuccess("The board is created!");
        return new ResponseEntity<>(apiSuccess, HttpStatus.CREATED);
    }

}
