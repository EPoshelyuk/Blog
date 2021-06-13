package by.poshelyuk.blog.service;

import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.entity.Comment;

import java.util.List;

public interface CommentService {

    void addComment(Article article);

    List<Comment> getByArticleId(String id);

    Comment getCommentByIdAndArticleId(String articleId, String commentId);
}