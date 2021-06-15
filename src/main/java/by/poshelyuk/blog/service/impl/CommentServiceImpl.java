package by.poshelyuk.blog.service.impl;

import by.poshelyuk.blog.dao.CommentDAO;
import by.poshelyuk.blog.entity.Comment;
import by.poshelyuk.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDAO commentDAO;

    @Override
    @Transactional
    public void addComment(Comment comment) {
        commentDAO.addComment(comment);
    }

    @Override
    @Transactional
    public List<Comment> getByArticleId(String id) {
        return commentDAO.getCommentByArticleId(id);
    }

    @Override
    @Transactional
    public Comment getCommentByIdAndArticleId(String articleId, String commentId) {
        return commentDAO.getByCommentIdAndArticleId(commentId, articleId);
    }


}
