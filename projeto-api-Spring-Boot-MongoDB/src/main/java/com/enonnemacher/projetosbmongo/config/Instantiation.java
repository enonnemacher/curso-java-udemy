package com.enonnemacher.projetosbmongo.config;

import com.enonnemacher.projetosbmongo.domain.Post;
import com.enonnemacher.projetosbmongo.domain.User;
import com.enonnemacher.projetosbmongo.dto.AuthorDTO;
import com.enonnemacher.projetosbmongo.dto.CommentDTO;
import com.enonnemacher.projetosbmongo.repository.PostRepository;
import com.enonnemacher.projetosbmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, simpleDateFormat.parse("01/01/2022"), "Feliz ano novo", "Ate ano que vem", new AuthorDTO(maria));
        Post post2 = new Post(null, simpleDateFormat.parse("03/01/2022"), "Partiu praia", "Ate mais ver", new AuthorDTO(maria));
        postRepository.saveAll(Arrays.asList(post1, post2));

        CommentDTO comment1 = new CommentDTO("Boa viagem!", simpleDateFormat.parse("05/01/2022"), new AuthorDTO(alex));
        CommentDTO comment2 = new CommentDTO("Aproveite as ferias!", simpleDateFormat.parse("05/01/2022"), new AuthorDTO(bob));
        CommentDTO comment3 = new CommentDTO("Aproveite a praia!", simpleDateFormat.parse("05/01/2022"), new AuthorDTO(alex));

        post1.getComments().add(comment1);
        post2.getComments().addAll(Arrays.asList(comment2, comment3));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }
}
