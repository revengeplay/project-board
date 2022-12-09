package com.project.board.service;

import com.project.board.domain.Article;
import com.project.board.domain.ArticleComment;
import com.project.board.dto.ArticleCommentDto;
import com.project.board.repository.ArticleCommentRepository;
import com.project.board.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@DisplayName("댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks private ArticleCommentService sut;

    @Mock private ArticleCommentRepository articleCommentRepository;
    @Mock private ArticleRepository articleRepository;

    @Test
    void given_when_thenRead() {
        // Given
        Long articleId = 1L;

        given(articleRepository.findById(articleId)).willReturn(Optional.of(Article.of("title","content","#java")));

        // When
        List<ArticleCommentDto> articleComments = sut.searchArticleComment(articleId);


        // Then
        assertThat(articleComments).isNotNull();
        BDDMockito.then(articleRepository).should().findById(articleId);

    }

    
}
