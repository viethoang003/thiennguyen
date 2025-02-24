//package com.example.backend.controller;
//
//import com.example.backend.dto.PostRequest;
//import com.example.backend.model.Post;
//import com.example.backend.model.User;
//import com.example.backend.service.PostService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import com.example.backend.model.PostStatus;
//
//@RestController
//@RequestMapping("/api/posts")
//public class PostController {
//
//    @Autowired
//    private PostService postService;
//
//    @GetMapping("/search")
//    public ResponseEntity<List<Post>> searchPosts(
//            @RequestParam(required = false) String keyword,
//            @RequestParam(required = false) PostStatus status) {
//        List<Post> posts = postService.searchPosts(keyword, status);
//        return ResponseEntity.ok(posts);
//    }
//
//    @GetMapping
//    public ResponseEntity<Page<Post>> getAllPosts(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Post> posts = postService.getAllPosts(pageable);
//        return ResponseEntity.ok(posts);
//    }
//
//    @GetMapping("/{postId}")
//    public ResponseEntity<Post> getPostById(@PathVariable Long postId) {
//        Post post = postService.getPostById(postId);
//        return ResponseEntity.ok(post);
//    }
//
//    @PostMapping
//    public ResponseEntity<Post> createPost(@RequestBody Post post, @AuthenticationPrincipal User user) {
//        Post createdPost = postService.createPost(post, user);
//        return ResponseEntity.ok(createdPost);
//    }
//
//    @PutMapping("/{postId}")
//    public ResponseEntity<Post> updatePost(
//            @PathVariable Long postId,
//            @RequestBody Post updatedPost,
//            @AuthenticationPrincipal User user) {
//        Post post = postService.updatePost(postId, updatedPost, user);
//        return ResponseEntity.ok(post);
//    }
//
//    @DeleteMapping("/{postId}")
//    public ResponseEntity<Void> deletePost(@PathVariable Long postId, @AuthenticationPrincipal User user) {
//        postService.deletePost(postId, user);
//        return ResponseEntity.noContent().build();
//    }
//    @PostMapping
//    public ResponseEntity<Post> createPost(
//            @Valid @RequestBody PostRequest postRequest, // Thêm @Valid
//            @AuthenticationPrincipal User user
//    ) {
//        Post post = postService.createPost(postRequest, user);
//        return ResponseEntity.ok(post);
//    }
//}
package com.example.backend.controller;

import com.example.backend.dto.PostRequest;
import com.example.backend.model.Post;
import com.example.backend.model.User;
import com.example.backend.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.example.backend.model.PostStatus;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/search")
    public ResponseEntity<List<Post>> searchPosts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) PostStatus status) {
        List<Post> posts = postService.searchPosts(keyword, status);
        return ResponseEntity.ok(posts);
    }

    @GetMapping
    public ResponseEntity<Page<Post>> getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> posts = postService.getAllPosts(pageable);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Long postId) {
        Post post = postService.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(
            @Valid @RequestBody PostRequest postRequest, // Thêm @Valid
            @AuthenticationPrincipal User user
    ) {
        Post post = postService.createPost(postRequest, user);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(
            @PathVariable Long postId,
            @RequestBody Post updatedPost,
            @AuthenticationPrincipal User user) {
        Post post = postService.updatePost(postId, updatedPost, user);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId, @AuthenticationPrincipal User user) {
        postService.deletePost(postId, user);
        return ResponseEntity.noContent().build();
    }
}