package com.taskcodee.server.services;

import com.taskcodee.server.dto.users.UserCreationDTO;
import com.taskcodee.server.entities.User;
import com.taskcodee.server.exceptions.EntityAlreadyExistsException;
import com.taskcodee.server.exceptions.MyEntityNotFoundException;
import com.taskcodee.server.mappers.UserMapper;
import com.taskcodee.server.repositoires.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new MyEntityNotFoundException(id));
    }

    public User getReferenceById(Long id) {
        return userRepository.getReferenceById(id);
    }

    public User save(UserCreationDTO userCreationDTO) {
        try {
            return userRepository.save(userMapper.mapToUserEntity(userCreationDTO));
        } catch (DataIntegrityViolationException ex) {
            throw new EntityAlreadyExistsException(ex.getMessage());
        }
    }

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }
}
