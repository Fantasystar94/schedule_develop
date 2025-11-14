package com.example.schedule_develop.repository;

import com.example.schedule_develop.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
