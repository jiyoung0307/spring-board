package com.spring.board.controller;

import com.spring.board.dto.ArticleFormDTO;
import com.spring.board.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.spring.board.repository.ArticleRepository;

@Slf4j          // 로깅 기능을 위한 어노테이션 추가
@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleFormDTO articleFormDTO) {
        log.info(articleFormDTO.toString());

        /* 1. DTO를 엔티티로 변환 */
        Article article = articleFormDTO.toEntity();
        log.info(article.toString());

        /* 2. 리파지터리로 엔티티를 DB에 저장 */
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        return "";
    }
}
