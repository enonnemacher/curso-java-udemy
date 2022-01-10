package com.enonnemacher.projetosbmongo.repository;

import com.enonnemacher.projetosbmongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
