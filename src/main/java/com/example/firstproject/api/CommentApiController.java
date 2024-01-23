package com.example.firstproject.api;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // REST 컨트롤러 선언
public class CommentApiController {

    @Autowired // 댓글 서비스 객체 주입
    private CommentService commentService;

    @GetMapping("/api/articles/{articleId}/comments") // 댓글 조회 요청 접수
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        // 서비스의 위임
        List<CommentDto> dtos = commentService.comments(articleId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @PostMapping("/api/articles/{articleId}/comments")// 댓글 생성 요청 접수
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId,
                                             @RequestBody CommentDto dto){
                                    // create() 메서드 생성
        // 서비스에 위임
        CommentDto createDto = commentService.create(articleId, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createDto);
    }

    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id,
                                             @RequestBody CommentDto dto){
        // 서비스에 위임
        CommentDto updatedDto = commentService.update(id, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }


}
