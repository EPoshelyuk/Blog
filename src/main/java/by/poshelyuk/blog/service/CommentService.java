package by.poshelyuk.blog.service;

import by.poshelyuk.blog.entity.Comment;

import java.util.List;

public interface CommentService {

    void addComment(Comment comment);

    List<Comment> findByArticleId(String id);

    Comment getCommentByIdAndArticleId(String articleId, String commentId);

    List<Comment> findAll(Integer skip, Integer limit, String sort, String order);

    Comment findById(String commentId);
}
