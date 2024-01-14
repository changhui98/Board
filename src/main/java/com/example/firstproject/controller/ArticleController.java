package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create") // URL 요청 접수
    public String createArticle(ArticleForm form){ // 메서드 생성 및 반환값 작성
        log.info(form.toString()); // 로깅 코드 추가

        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString()); // 로깅 코드 추가

        // 2. 리포지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString()); // 로깅 코드 추가

        return "";
    }
    @GetMapping("/articles/{id}")  // 데이터 조회 요청 접수
    public String show(@PathVariable Long id){ // 매개변수로 id 받아 오기
        log.info("id = " + id);
        return "";
    }
}