package com.taskcodee.server.controllers;

import com.taskcodee.server.APIs.ApiSuccess;
import com.taskcodee.server.DTOs.BoardCreationDTO;
import com.taskcodee.server.services.BoardMemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BoardMemberController {

    @Autowired
    private BoardMemberService boardMemberService;

    @PostMapping("/boardMembers")
    public ResponseEntity<Object> createBoardMember(@Valid @RequestBody BoardCreationDTO boardCreationDTO) {

        ApiSuccess apiSuccess = new ApiSuccess("The user is created!");
        return new ResponseEntity<>(apiSuccess, HttpStatus.CREATED);
    }
}
