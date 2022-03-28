package com.sparta.solo2.repository;

import com.sparta.solo2.model.Comments;
import com.sparta.solo2.model.Contents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentsRepository extends JpaRepository<Comments, Long> {
    List<Comments> findAllByOrderByModifiedAtDesc();
}