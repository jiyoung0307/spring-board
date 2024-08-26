package com.spring.board.controller;

import com.spring.board.dto.ArticleFormDTO;
import com.spring.board.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.spring.board.repository.ArticleRepository;

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
        System.out.println("articleFormDTO.toString() : " + articleFormDTO.toString());
        /* 1. DTO를 엔티티로 변환 */
        Article article = articleFormDTO.toEntity();
        System.out.println("article.toString() : " + article.toString());
        /* 2. 리파지터리로 엔티티를 DB에 저장 */
        Article saved = articleRepository.save(article);
        System.out.println("saved.toString() : " + saved.toString());
        return "";
    }
}
