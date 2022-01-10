package com.enonnemacher.projetosbmongo.service;

import com.enonnemacher.projetosbmongo.domain.User;
import com.enonnemacher.projetosbmongo.dto.UserDTO;
import com.enonnemacher.projetosbmongo.exception.NotFoundException;
import com.enonnemacher.projetosbmongo.repository.UserRepository;
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

    public User findById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new NotFoundException("Objeto nao encontrado."));
    }

    public User insert(User user) {
        return userRepository.insert(user);
    }

    public User fromDTO(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }
}
