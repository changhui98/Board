package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleService articleService; // 서비스 객체 주입

//    @Autowired
//    private ArticleRepository articleRepository; // 게시글 리포지터리 주입

    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleService.index();
    }


//    // GET
//    @GetMapping("/api/articles") // URL 요청 접수
//    public List<Article> index(){ // index() 메서드 정의
//        return articleRepository.findAll();
//    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleService.show(id);
    }
//    @GetMapping("/api/articles/{id}") // 코드를 붙여 넣고 URL 수정
//    public Article show(@PathVariable Long id) { // show() 메서드로 수정 , URL의 id를 매개변수로 받아오기
//
//        return articleRepository.findById(id).orElse(null);
//    }

    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){
        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
//    // POST
//    @PostMapping("/api/articles")
//    public Article create(@RequestBody ArticleForm dto){
//        Article article = dto.toEntity();
//        return articleRepository.save(article);
//    }

    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto){
        Article updated = articleService.update(id, dto);   // 서비스를 통해 게시글 수정
        return ( updated != null) ?                         // 수정되면 정상, 안 되면 오류 응답
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


//    // PATCH
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> update(@PathVariable Long id,
//                                         @RequestBody ArticleForm dto){
//        // 1. DTO -> 엔티티 변환하기
//        Article article = dto.toEntity();
//        log.info("id: {}, article: {}", id, article.toString());
//        // 2. 타깃 조회하기
//        Article target = articleRepository.findById(id).orElse(null);
//        // 3. 잘못된 요청 처리하기
//        if(target == null || id != article.getId()){
//            // 400, 잘못된 요청 응답
//            log.info("잘못된 요청! id: {} , article: {}", id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        // 4. 업데이트 및 정상 응답(200)하기
//        target.patch(article); // 기존 데이터에 새 데이터 붙이기
//        Article updated = articleRepository.save(target); // 수정 내용 DB에 최종 저장
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
//    // DELETE
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id) {
//        // 1. 대상 찾기
//        Article target = articleRepository.findById(id).orElse(null);
//
//        // 2. 잘못된 요청 처리하기
//        if(target == null){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        // 3. 대상 삭제하기
//        articleRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).build();
//
//
//    }

    @PostMapping("/api/transaction-test") // 여러 게시글 생성 요청 접수
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
        List<Article> createdList = articleService.createArticles(dtos); // 서비스 호출
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    } // transactionTest() 메서드 정의
}

