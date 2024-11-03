package com.spring.board.api;

import com.spring.board.dto.ArticleFormDTO;
import com.spring.board.entity.Article;
import com.spring.board.service.ArticleService;
import com.spring.board.service.ArticleTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class ArticleApiTransactionController {

    @Autowired
    private ArticleTransactionService articleTransactionService;

    @PostMapping("/transaction-test")   // 여러 게시글 생성 요청 접수
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleFormDTO> articleFormDTO) {
        List<Article> createdList = articleTransactionService.createArticles(articleFormDTO);   // 서비스 호출
        return (createdList != null) ?  // 생성 결과에 따라 응답 처리
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
