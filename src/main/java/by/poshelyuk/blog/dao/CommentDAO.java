package by.poshelyuk.blog.dao;

import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.entity.Comment;

import java.util.List;

public interface CommentDAO {
    List<Comment> getCommentByArticleId(String id);

    Comment getByCommentIdAndArticleId(String commentId, String articleId);

    void addComment(Comment comment);
}
