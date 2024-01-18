package com.example.firstproject.api;

import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleApiController {

    @Autowired
    private ArticleRepository articleRepository; // 게시글 리포지터리 주입

    // GET
    @GetMapping("api/articles") // URL 요청 접수
    public List<Article> index(){ // index() 메서드 정의
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}") // 코드를 붙여 넣고 URL 수정
    public Article show(@PathVariable Long id) { // show() 메서드로 수정 , URL의 id를 매개변수로 받아오기

        return articleRepository.findById(id).orElse(null);
    }
    // POST
    // PATCH
    // DELETE
}
