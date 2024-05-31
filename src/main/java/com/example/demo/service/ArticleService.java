package com.example.demo.service;

import java.util.List;

import com.example.demo.exceptions.InvalidReferenceException;
import com.example.demo.exceptions.NullValueException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.ArticleRepositoryMemory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.controller.dto.request.ArticleCreateRequest;
import com.example.demo.controller.dto.response.ArticleResponse;
import com.example.demo.controller.dto.request.ArticleUpdateRequest;
import com.example.demo.domain.Article;
import com.example.demo.domain.Board;
import com.example.demo.domain.Member;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.MemberRepository;

@Service
@Transactional(readOnly = true)
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public ArticleService(
        ArticleRepository articleRepository,
        MemberRepository memberRepository,
        BoardRepository boardRepository
    ) {
        this.articleRepository = articleRepository;
        this.memberRepository = memberRepository;
        this.boardRepository = boardRepository;
    }

    public ArticleResponse getById(Long id) {
        Article article = articleRepository.findById(id);
        Member member = memberRepository.findById(article.getAuthorId());
        Board board = boardRepository.findById(article.getBoardId());

        return ArticleResponse.of(article, member, board);
    }

    public List<ArticleResponse> getByBoardId(Long boardId) {
        List<Article> articles = articleRepository.findAllByBoardId(boardId);
        return articles.stream()
            .map(article -> {
                Member member = memberRepository.findById(article.getAuthorId());
                Board board = boardRepository.findById(article.getBoardId());

                return ArticleResponse.of(article, member, board);
            })
            .toList();
    }

    @Transactional
    public ArticleResponse create(ArticleCreateRequest request) {
        if (request.author_id() == null || request.board_id() == null || request.title() == null || request.content() == null) {
            throw new NullValueException("요청에 필요한 항목이 누락됐습니다.");
        }

        Article article = new Article(
            request.author_id(),
            request.board_id(),
            request.title(),
            request.content()
        );
        Article saved = articleRepository.insert(article);
        Member member = memberRepository.findById(saved.getAuthorId());
        Board board = boardRepository.findById(saved.getBoardId());

        return ArticleResponse.of(saved, member, board);
    }

    @Transactional
    public ArticleResponse update(Long id, ArticleUpdateRequest request) {
        if (request.board_id() == null || request.title() == null || request.content() == null) {
            throw new NullValueException("요청에 필요한 항목이 누락됐습니다.");
        }

        Article article = articleRepository.findById(id);
        article.update(request.board_id(), request.title(), request.content());
        Article updated = articleRepository.update(article);

        Member member = memberRepository.findById(updated.getAuthorId());
        Board board = boardRepository.findById(article.getBoardId());

        return ArticleResponse.of(article, member, board);
    }

    @Transactional
    public void delete(Long id) {
        Article article = articleRepository.findById(id);

        articleRepository.deleteById(id);
    }
}
