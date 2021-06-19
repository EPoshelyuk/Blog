package by.poshelyuk.blog.service.impl;

import by.poshelyuk.blog.entity.Comment;

import by.poshelyuk.blog.filtration.Page;
import by.poshelyuk.blog.filtration.impl.CommentSortProvider;
import by.poshelyuk.blog.queries.CommentQueryRepository;
import by.poshelyuk.blog.repository.CommentRepository;
import by.poshelyuk.blog.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentQueryRepository commentQueryRepository;

    public CommentServiceImpl(CommentRepository commentRepository, CommentQueryRepository commentQueryRepository) {
        this.commentRepository = commentRepository;
        this.commentQueryRepository = commentQueryRepository;
    }

    @Override
    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> findByArticleId(String id) {
        return commentRepository.findByArticleId(id);
    }

    @Override
    public Comment getCommentByIdAndArticleId(String articleId, String commentId) {
        return commentRepository.getCommentByIdAndArticleId(articleId, commentId);
    }

    @Override
    public List<Comment> findAll(Integer skip, Integer limit, String sort, String order) {
        return commentQueryRepository.findAll(new Page(skip, limit), new CommentSortProvider(sort, order));
    }


}
