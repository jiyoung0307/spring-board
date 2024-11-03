package com.spring.board.API;

import com.spring.board.dto.ArticleFormDTO;
import com.spring.board.entity.Article;
import com.spring.board.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class ArticleApiController {
    @Autowired  // 게시글 레퍼지터리 주입
    private ArticleRepository articleRepository;

    // GET
    @GetMapping("/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }

    @GetMapping("/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    // POST
    @PostMapping("/articles")   // URL 요청 접수
    /* 게시판 폼을 만들고 데이터를 생성할 떄는 컨트롤러의 메서드에 매개변수로 DTO를 받아오기만 하면 됐었다.
    * 그러나 REST API에서는 데이터를 생성할 떄 JSON 데이터를 받아와야 하므로 단순히 매개변수로 DTO를 쓴다고 해서 받아올 수 있지 않다.
    * DTO 매개변수 앞에 @RequestBody 어노테이션을 추가해줘야 한다.
    * 이렇게 하면 요청 시 본문(Body)에 실어 보내는 데이터를 create()의 매개변수로 받아올 수 있다.
    */
    public Article create(@RequestBody ArticleFormDTO articleFormDTO) {
        Article article = articleFormDTO.toEntity();
        return articleRepository.save(article);
    }

    // PATCH
    @PatchMapping("/articles/{id}")   // URL 요청 접수
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleFormDTO articleFormDTO) {  // 반환형 수정(Article -> ResponseEntity<Article>)
        // 1. 수정용 엔티티 생성하기
        // 1-1. DTO -> 엔티티 변환하기
        Article article = articleFormDTO.toEntity();    // DTO를 엔티티로 변환
        log.info("id: {}, article: {}", id, article.toString());    // 로그 찍기

        // 2. DB에 대상 엔티티가 있는지 조회하기
        // 2-1. 타깃 조회하기
        Article updateTarget = articleRepository.findById(id).orElse(null);

        // 3. 대상 엔티티가 없거나 수정하려는 id가 잘못됐을 경우 처리하기
        // 3-1. 잘못된 요청 처리하기
        if(updateTarget == null || id != article.getId()) {     // 잘못된 요청인지 판별
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());  // 로그 찍기
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);    // ResponseEntity 반환
        }

        // 4. 대상 엔티티가 있으면 수정 내용으로 업데이트하고 정상 응답(200) 보내기
        // 4-1. 업데이트 및 정상 응답(200)하기
        updateTarget.patch(article);    // 기존 데이터에 새 데이터 붙이기
        Article updated = articleRepository.save(updateTarget);  // 수정 내용 DB에 최종 저장
        return ResponseEntity.status(HttpStatus.OK).body(updated);  // 정상 응답
    }

    // DELETE
    @DeleteMapping("/articles/{id}")   // URL 요청 접수
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        // 1. DB에서 대상 엔티티가 있는지 조회하기
        // 1-1. 대상 찾기
        Article deleteTarget = articleRepository.findById(id).orElse(null);

        // 2. 대상 엔티티가 없어서 요청 자체가 잘못됐을 경우 처리하기
        // 2-1. 잘못된 요청 처리하기
        if(deleteTarget == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 3. 대상 엔티티가 있으면 삭제하고 정상 응답(200) 반환하기
        // 3-1. 대상 삭제하기
        articleRepository.delete(deleteTarget);
        /* return ResponseEntity.status(HttpStatus.OK).body(null); */
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
