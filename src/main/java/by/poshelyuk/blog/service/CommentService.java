package by.poshelyuk.blog.service;

import by.poshelyuk.blog.dto.CommentDto;

import java.util.List;

public interface CommentService {

    void addComment(CommentDto commentDto);

    List<CommentDto> findByArticleId(String id);

    CommentDto getCommentByIdAndArticleId(String articleId, String commentId);

    List<CommentDto> findAll(Integer skip, Integer limit, String sort, String order);

    CommentDto findById(String commentId);
}
