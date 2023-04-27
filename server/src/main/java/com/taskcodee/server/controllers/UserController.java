package com.taskcodee.server.controllers;

import com.taskcodee.server.APIs.ApiSuccess;
import com.taskcodee.server.DTOs.UserCreationDTO;
import com.taskcodee.server.entities.User;
import com.taskcodee.server.exceptions.MyEntityNotFoundException;
import com.taskcodee.server.services.MappingUtils;
import com.taskcodee.server.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MappingUtils mappingUtils;

//    @GetMapping("/users")
//    public List<User> getUsers() {
//        return userService.findAll().stream().map(mappingUtils::);
//    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserCreationDTO userCreationDTO) {
        userService.save(userCreationDTO);
        ApiSuccess apiSuccess = new ApiSuccess("The user is created!");
        return new ResponseEntity<>(apiSuccess, HttpStatus.CREATED);
    }
}
