package com.spring.board.controller;

import com.spring.board.dto.ArticleFormDTO;
import com.spring.board.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.spring.board.repository.ArticleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")   // 데이터 조회 요청 접수
    public String show(@PathVariable Long id, Model model) {     // 매개변수로 id 받아오기
        log.info("id : " + id);    // id를 잘 받았는지 확인하는 로그 찍기

        /* 1. id를 조회해 데이터 가져오기 */
        Article articleEntity = articleRepository.findById(id).orElse(null);

        /* 2. 모델에 데이터 등록하기 */
        model.addAttribute("article", articleEntity);

        /* 3. 뷰 페이지 반환하기 */
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {  // model 객체 받아오기
        /* 1. DB에서 모든 Article 데이터 가져오기 */
        List<Article> articleEntityList = articleRepository.findAll();

        /* 2. 가져온 Article 묶음을 모델에 등록하기 */
        model.addAttribute("articleList", articleEntityList);   // articleEntityList 등록

        /* 3. 사용자에게 보여 줄 뷰 페이지 설정하기 */
        return "articles/index";
    }
}
