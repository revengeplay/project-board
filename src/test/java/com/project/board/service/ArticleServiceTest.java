package com.project.board.service;

import com.project.board.domain.Article;
import com.project.board.domain.type.SearchType;
import com.project.board.dto.ArticleDto;
import com.project.board.dto.ArticleUpdateDto;
import com.project.board.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;


@DisplayName("게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks
    private ArticleService sut;

    @Mock
    private ArticleRepository articleRepository;

    /*
    검색
    각 게시글 페이지로 이동
    페이지네이션
    홈 버튼
    정렬 기능
     */
    @DisplayName("게시글을 검색하면, 게시글 리스트를 반환한다.")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnsArticleList() {
        // Given

        // When
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE,"search keyword");
//        Page<ArticleDto> articles =  sut.searchArticles(SearchType.TITLE,"search keyword");

        // Then
        assertThat(articles).isNotNull();


    }

    @DisplayName("게시글을 조회하면, 각 게시글 페이지로 이동")
    @Test
    void givenArticleId_whenSearchingArticle_thenReturnsArticle() {
        // Given

        // When
        ArticleDto articles = sut.searchArticles(1L);

        // Then
        assertThat(articles).isNotNull();


    }

    @DisplayName("게시글 정보를 입력하면, 게시글을 생성한다")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSavesArticle(){
        // Given
        given(articleRepository.save(any(Article.class))).willReturn(null);

        // When
        sut.saveArticle(ArticleDto.of(LocalDateTime.now(),"rev","title","content","hashtag"));

        // Then
        BDDMockito.then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글 정보를 입력하면, 게시글을 수정한다")
    @Test
    void givenArticleInfo_whenModifyingArticle_thenModifiesArticle(){
        // Given
        given(articleRepository.save(any(Article.class))).willReturn(null);

        // When
        sut.updateArticle(1L, ArticleUpdateDto.of("title","content","hashtag"));

        // Then
        BDDMockito.then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글 정보를 입력하면, 게시글을 삭제한다")
    @Test
    void givenArticleInfo_whenDeletingArticle_thenDeletesArticle(){
        // Given
        willDoNothing().given(articleRepository).delete(any(Article.class));

        // When
        sut.deleteArticle(1L);

        // Then
        BDDMockito.then(articleRepository).should().delete(any(Article.class));
    }


}
