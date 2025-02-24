package com.example.backend.model;

public enum PostStatus {
    PENDING,    // Bài đăng đang chờ xử lý
    RESOLVED,   // Bài đăng đã được giải quyết
    IN_PROGRESS // Bài đăng đang được hỗ trợ (nếu cần)
}