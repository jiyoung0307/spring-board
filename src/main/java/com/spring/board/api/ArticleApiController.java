package com.spring.board.api;

import com.spring.board.dto.ArticleFormDTO;
import com.spring.board.entity.Article;
import com.spring.board.service.ArticleService;
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
    @Autowired
    private ArticleService articleService;  // 서비스 객체 주입

    // GET
    @GetMapping("/articles")
    public List<Article> index() {
        return articleService.index();
    }

    @GetMapping("/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }


    // POST
    /* 게시판 폼을 만들고 데이터를 생성할 떄는 컨트롤러의 메서드에 매개변수로 DTO를 받아오기만 하면 됐었다.
     * 그러나 REST API에서는 데이터를 생성할 떄 JSON 데이터를 받아와야 하므로 단순히 매개변수로 DTO를 쓴다고 해서 받아올 수 있지 않다.
     * DTO 매개변수 앞에 @RequestBody 어노테이션을 추가해줘야 한다.
     * 이렇게 하면 요청 시 본문(Body)에 실어 보내는 데이터를 create()의 매개변수로 받아올 수 있다.
     */
    @PostMapping("/articles")   // URL 요청 접수
    public ResponseEntity<Article> create(@RequestBody ArticleFormDTO articleFormDTO) {
        /* create()를 호출할 때 POST 요청 메세지에 담긴 데이터도 전달해야 하므로 괄호 안에 DTO를 넣음 */
        Article regist = articleService.create(articleFormDTO);    // 서비스 게시글 생성
        return (regist != null) ?
                ResponseEntity.status(HttpStatus.OK).body(regist) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH
    /* 컨트롤러의 역할 : 클라이언트 요청 받기 */
    @PatchMapping("/articles/{id}")   // URL 요청 접수
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleFormDTO articleFormDTO) {
        Article updated = articleService.update(id, articleFormDTO);    // 서비스를 통해 게시글 수정
        return (updated != null) ?  // 수정되면 정상, 안 되면 오류 응답
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE
    @DeleteMapping("/articles/{id}")   // URL 요청 접수
    public ResponseEntity<Article> delete(@PathVariable Long id) {
       Article deleted = articleService.delete(id);     // 서비스를 통해 게시글 삭제
        return (deleted != null) ?  // 삭제 결과에 따라 응답 처리
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
