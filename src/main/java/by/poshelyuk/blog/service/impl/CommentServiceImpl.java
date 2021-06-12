package by.poshelyuk.blog.service.impl;

import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.entity.Comment;
import by.poshelyuk.blog.repository.CommentRepository;
import by.poshelyuk.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public void addComment(Article article) {

    }

    @Override
    public List<Comment> getByArticleId(String id) {
        return commentRepository.findCommentByArticleId(id);
    }

    @Override
    public Comment getCommentByIdAndArticleId(String articleId, String commentId) {
        return commentRepository.findByCommentIdAndArticle_ArticleId(commentId, articleId);
    }


}
