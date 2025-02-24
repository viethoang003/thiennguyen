package com.example.backend.repository;

import com.example.backend.model.Post;
import com.example.backend.model.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAll();
    List<Post> findByTitleContaining(String keyword);
    List<Post> findByStatus(PostStatus status);
    List<Post> findByTitleContainingAndStatus(String keyword, PostStatus status);
}