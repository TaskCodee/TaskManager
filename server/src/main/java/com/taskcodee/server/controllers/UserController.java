package com.taskcodee.server.controllers;

import com.taskcodee.server.api.ApiSuccess;
import com.taskcodee.server.dto.users.UserCreationDTO;
import com.taskcodee.server.dto.users.UserDTO;
import com.taskcodee.server.entities.User;
import com.taskcodee.server.mappers.UserMapper;
import com.taskcodee.server.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

     @GetMapping("/users")
     public List<UserDTO> getUsers() {
         return userService.findAll().stream().map(userMapper::mapToUserDTO).collect(Collectors.toList());
     }

    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
         User user = userService.findById(id);
         return userMapper.mapToUserDTO(user);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserCreationDTO userCreationDTO) {
        User user = userService.save(userCreationDTO);
        ApiSuccess apiSuccess = new ApiSuccess("The user is created!", userMapper.mapToUserDTO(user));
        return new ResponseEntity<>(apiSuccess, HttpStatus.CREATED);
    }
}
