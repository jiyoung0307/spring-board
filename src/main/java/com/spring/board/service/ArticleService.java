package com.spring.board.service;

import com.spring.board.dto.ArticleFormDTO;
import com.spring.board.entity.Article;
import com.spring.board.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service    // 서비스 객체 생성
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;    // 게시글 리파지터리 객체 주입

    public List<Article> index() {
        return articleRepository.findAll();  // DB에서 조회한 결과 반환
    }

    public Article show(Long id) {
        /* DB에서 id로 조회한 결과 반환 / 만약 조회 결과 데이터가 없으면 null 반환 */
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleFormDTO articleFormDTO) {
        /* 1. DTO -> 엔티티로 변환한 후 article에 저장 */
        Article article = articleFormDTO.toEntity();
        /* id는 데이터를 생성할 때 DB가 알아서 생성해주므로 필요가 없다.
           따라서, article 객체에 id가 존재한다면 null을 반환하는 코드 추가.
        */
        if(article.getId() != null) {
            return  null;
        }

        /* 2. article을 DB에 저장 */
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleFormDTO articleFormDTO) {
        /* 1. DTO -> 엔티티 변환하기 */
        Article article = articleFormDTO.toEntity();
        log.info("id: {}, article: {}", id, article.toString());

        /* 2. 타깃 조회하기 */
        Article updateTarget = articleRepository.findById(id).orElse(null);

        /* 3. 잘못된 요청 처리하기 */
        if(updateTarget == null || id != article.getId()) {     // 잘못된 요청인지 판별
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null;    // 응답은 컨트롤러가 하므로 여기서는 null 반환
        }

        /* 4. 업데이트 하기 */
        updateTarget.patch(article);
        Article updated = articleRepository.save(updateTarget);
        return updated;    // 응답은 컨트롤러가 하므로 여기서는 수정 데이터만 반환
    }

    public Article delete(Long id) {
        /* 1. 대상 찾기 */
        Article deleteTarget = articleRepository.findById(id).orElse(null);

        /* 2. 잘못된 요청 처리하기 */
        if(deleteTarget == null) {
            return null;    // 응답은 컨트롤러가 하므로 여기서는 null 반환
        }

        /* 3. 대상 삭제하기 */
        articleRepository.delete(deleteTarget);
        return deleteTarget;    // DB에서 삭제한 대상을 컨트롤러에 반환
    }
}
