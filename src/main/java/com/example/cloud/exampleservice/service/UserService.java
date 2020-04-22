package com.example.cloud.exampleservice.service;

import com.example.cloud.exampleservice.dto.UserDTO;
import com.example.cloud.exampleservice.model.User;
import com.example.cloud.exampleservice.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(UserDTO userDTO){
        User userToBeAdded = new User();
        BeanUtils.copyProperties(userDTO, userToBeAdded);
        return userRepository.save(userToBeAdded);
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId).get();
    }

    public User getUserByName(String userName) {
        return userRepository.findByName(userName).get();
    }
}
