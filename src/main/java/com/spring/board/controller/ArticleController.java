package com.spring.board.controller;

import com.spring.board.dto.ArticleFormDTO;
import com.spring.board.dto.CommentDTO;
import com.spring.board.entity.Article;
import com.spring.board.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.spring.board.repository.ArticleRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j          // 로깅 기능을 위한 어노테이션 추가
@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;

    /* Create */
    @GetMapping("/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/create")
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

    /* Read */
    @GetMapping("/{id}")   // 데이터 조회 요청 접수
    public String show(@PathVariable Long id, Model model) {     // 매개변수로 id 받아오기
        log.info("id : " + id);    // id를 잘 받았는지 확인하는 로그 찍기

        /* 1. id를 조회해 데이터 가져오기 */
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDTO> commentDTO = commentService.comments(id);

        /* 2. 모델에 데이터 등록하기 */
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDTO", commentDTO);

        /* 3. 뷰 페이지 반환하기 */
        return "articles/show";
    }

    @GetMapping("")
    public String index(Model model) {  // model 객체 받아오기
        /* 1. DB에서 모든 Article 데이터 가져오기 */
        List<Article> articleEntityList = articleRepository.findAll();

        /* 2. 가져온 Article 묶음을 모델에 등록하기 */
        model.addAttribute("articleList", articleEntityList);   // articleEntityList 등록

        /* 3. 사용자에게 보여 줄 뷰 페이지 설정하기 */
        return "articles/index";
    }

    /* Update */
    @GetMapping("/{id}/edit")  // URL 요청 접수
    public String edit(@PathVariable Long id, Model model) {  // id를 매개변수로 받아 오기
        // DB에서 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);   // articleEntity를 article로 등록

        // 뷰 페이지 설정
        return "articles/edit";
    }

    @PostMapping("/update")  // URL 요청 접수
    public String update(ArticleFormDTO articleFormDTO) {  // 매개변수로 DTO 받아오기
        log.info(articleFormDTO.toString());

        // 1. DTO를 엔티티로 변환하기
        Article articleEntity = articleFormDTO.toEntity();   // DTO를 엔티티로 변환
        log.info(articleEntity.toString());

        // 2. 엔티티를 DB에 저장하기
        // 2-1. DB에서 기존 데이터 가져오기
        Article updateTarget = articleRepository.findById(articleEntity.getId()).orElse(null);

        // 2-2. 기존 데이터 값을 갱신하기
        if(updateTarget != null) {
            articleRepository.save(articleEntity);  // 엔티티를 DB에 저장(갱신)
        }
        // 3. 수정 결과 페이지를 리다이렉트하기
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/{id}/delete")  // URL 요청 접수
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {  // id를 매개변수로 받아 오기
        log.info("delete 요청이 들어옴!!!");
        // 1. 삭제할 대상 가져오기
        Article deleteTarget = articleRepository.findById(id).orElse(null);   // 데이터 찾기
        log.info("deleteTarget에 데이터 있나 : " + deleteTarget.toString());  // deleteTarget에 데이터가 있는지 확인

        // 2. 대상 엔티티 삭제하기
        if(deleteTarget != null) {  // 삭제할 대상이 있는지 확인
            articleRepository.delete(deleteTarget); // delete()로 대상 삭제
            rttr.addFlashAttribute("msg", "삭제되었습니다!");
        }

        // 3. 결과 페이지로 리다이렉트하기
        return "redirect:/articles";
    }
}