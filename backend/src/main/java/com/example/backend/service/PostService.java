package com.example.backend.service;

import com.example.backend.dto.PostRequest;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.exception.UnauthorizedException;
import com.example.backend.model.*;
import com.example.backend.repository.LocationRepository;
import com.example.backend.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private LocationRepository locationRepository;

    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    // Lấy bài đăng theo ID
    public Post getPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
    }

    // Tạo bài đăng mới
    public Post createPost(PostRequest postRequest, User user) {
        logger.info("Creating post for user: {}", user.getUsername());
        Location location = new Location();
        location.setLatitude(postRequest.getLatitude());
        location.setLongitude(postRequest.getLongitude());
        locationRepository.save(location);

        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setDescription(postRequest.getDescription());
        post.setUser(user);
        post.setLocation(location);
        return postRepository.save(post);
    }

    // Cập nhật bài đăng
    public Post updatePost(Long postId, Post updatedPost, User user) {
        Post post = getPostById(postId);

        // Kiểm tra quyền sở hữu
        if (!post.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException("You don't have permission to update this post");
        }

        post.setTitle(updatedPost.getTitle());
        post.setDescription(updatedPost.getDescription());
        return postRepository.save(post);
    }

    // Xóa bài đăng
    public void deletePost(Long postId, User user) {
        Post post = getPostById(postId);

        // Kiểm tra quyền sở hữu
        if (!post.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException("You don't have permission to delete this post");
        }

        postRepository.delete(post);
    }

    // Lấy tất cả bài đăng
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Thêm bình luận vào bài đăng
    public Post addCommentToPost(Long postId, Comment comment, User user) {
        Post post = getPostById(postId);
        comment.setPost(post);
        comment.setUser(user);
        post.getComments().add(comment);
        return postRepository.save(post);
    }

    // Xóa bình luận khỏi bài đăng
    public Post removeCommentFromPost(Long postId, Long commentId, User user) {
        Post post = getPostById(postId);
        Comment comment = post.getComments().stream()
                .filter(c -> c.getId().equals(commentId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + commentId));
        if (!comment.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException("You don't have permission to delete this comment");
        }
        post.getComments().remove(comment);
        return postRepository.save(post);
    }

    // Lấy tất cả bài đăng với phân trang
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    // Tìm kiếm bài đăng theo từ khóa và trạng thái
    public List<Post> searchPosts(String keyword, PostStatus status) {
        if (keyword != null && status != null) {
            return postRepository.findByTitleContainingAndStatus(keyword, status);
        } else if (keyword != null) {
            return postRepository.findByTitleContaining(keyword);
        } else if (status != null) {
            return postRepository.findByStatus(status);
        } else {
            return postRepository.findAll();
        }
    }
}