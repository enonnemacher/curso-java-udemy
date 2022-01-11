package com.enonnemacher.projetosbmongo.service;

import com.enonnemacher.projetosbmongo.domain.Post;
import com.enonnemacher.projetosbmongo.exception.NotFoundException;
import com.enonnemacher.projetosbmongo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post findById(String id) {
        Optional<Post> userPost = postRepository.findById(id);
        return userPost.orElseThrow(() -> new NotFoundException("Objeto nao encontrado."));
    }

    public List<Post> findPostByTitle(String text) {
        return postRepository.findByTitle(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return postRepository.fullSearch(text, minDate, maxDate);
    }
}
