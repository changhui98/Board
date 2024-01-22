package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        // 1. 댓글 조회
        // 2. 엔티티 -> DTO 변환
        // 3. 결과 반환
    }
}
