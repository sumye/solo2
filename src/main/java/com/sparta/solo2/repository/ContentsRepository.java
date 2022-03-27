package com.sparta.solo2.repository;

import com.sparta.solo2.model.Contents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ContentsRepository extends JpaRepository<Contents, Long> {
    List<Contents> findAllByOrderByModifiedAtDesc();
}