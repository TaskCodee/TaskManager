package com.taskcodee.server.controllers;

import com.taskcodee.server.services.BoardMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BoardMemberController {

    @Autowired
    private BoardMemberService boardMemberService;

}
