package io.lucasprojects.granna.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.lucasprojects.granna.domain.model.Title;

public interface TitleRepository extends JpaRepository<Title, Long> {
    
}
