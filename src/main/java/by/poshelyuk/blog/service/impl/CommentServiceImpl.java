package by.poshelyuk.blog.service.impl;

import by.poshelyuk.blog.dao.CommentDAO;
import by.poshelyuk.blog.entity.Comment;
import by.poshelyuk.blog.filtration.Page;
import by.poshelyuk.blog.filtration.impl.CommentSortProvider;
import by.poshelyuk.blog.queries.CommentQueryRepository;
import by.poshelyuk.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class CommentServiceImpl implements CommentService {

    private final CommentDAO commentDAO;
    private  final CommentQueryRepository commentQueryRepository;

    public CommentServiceImpl(CommentDAO commentDAO, CommentQueryRepository commentQueryRepository) {
        this.commentDAO = commentDAO;
        this.commentQueryRepository = commentQueryRepository;
    }

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
    @Transactional
    @Override
    public List<Comment> findAll(Integer skip, Integer limit, String sort, String order) {
        return commentQueryRepository.findAll(new Page(skip, limit), new CommentSortProvider(sort, order));

    }

}
