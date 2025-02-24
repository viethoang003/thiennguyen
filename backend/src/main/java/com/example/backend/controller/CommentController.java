package com.example.backend.controller;

import com.example.backend.model.Comment;
import com.example.backend.model.User;
import com.example.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(
            @PathVariable Long postId,
            @RequestBody Comment comment,
            @AuthenticationPrincipal User user) {
        Comment createdComment = commentService.createComment(postId, comment, user);
        return ResponseEntity.ok(createdComment);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @AuthenticationPrincipal User user) {
        commentService.deleteComment(postId, commentId, user);
        return ResponseEntity.noContent().build();
    }
}