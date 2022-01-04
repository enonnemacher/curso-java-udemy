package com.enonnemacher.course.service;

import com.enonnemacher.course.entities.User;
import com.enonnemacher.course.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id){
        Optional<User> result = userRepository.findById(id);
        return result.get();
    }
}

