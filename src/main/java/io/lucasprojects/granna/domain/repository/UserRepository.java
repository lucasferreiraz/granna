package io.lucasprojects.granna.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.lucasprojects.granna.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    List<User> findByEmail(String email);

}
