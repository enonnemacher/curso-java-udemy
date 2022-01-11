package com.enonnemacher.projetosbmongo.service;

import com.enonnemacher.projetosbmongo.domain.Post;
import com.enonnemacher.projetosbmongo.exception.NotFoundException;
import com.enonnemacher.projetosbmongo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post findById(String id) {
        Optional<Post> userPost = postRepository.findById(id);
        return userPost.orElseThrow(() -> new NotFoundException("Objeto nao encontrado."));
    }
}
