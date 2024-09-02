package com.scm.repositories;

import java.lang.foreign.Linker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    //extra method
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByEmailToken(String id);
    
}
