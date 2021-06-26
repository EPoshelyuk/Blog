package by.poshelyuk.blog.service.impl;

import by.poshelyuk.blog.dto.CommentDto;
import by.poshelyuk.blog.dto.converter.CommentConverter;
import by.poshelyuk.blog.entity.Comment;

import by.poshelyuk.blog.filtration.Page;
import by.poshelyuk.blog.filtration.impl.CommentSortProvider;
import by.poshelyuk.blog.queries.CommentQueryRepository;
import by.poshelyuk.blog.repository.CommentRepository;
import by.poshelyuk.blog.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentQueryRepository commentQueryRepository;
    private final CommentConverter commentConverter;

    public CommentServiceImpl(CommentRepository commentRepository, CommentQueryRepository commentQueryRepository, CommentConverter commentConverter) {
        this.commentRepository = commentRepository;
        this.commentQueryRepository = commentQueryRepository;
        this.commentConverter = commentConverter;
    }

    @Override
    public void addComment(CommentDto commentDto) {
        commentRepository.save(commentConverter.convertToEntity(commentDto));
    }

    @Override
    public List<CommentDto> findByArticleId(String id) {
        List<Comment> comments = commentRepository.findByArticleId(id);
        return comments.stream().map(commentConverter::convertToDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentByIdAndArticleId(String articleId, String commentId) {
        return commentConverter.convertToDto(commentRepository.getCommentByIdAndArticleId(articleId, commentId));
    }

    @Override
    public List<CommentDto> findAll(Integer skip, Integer limit, String sort, String order) {
        List<Comment> comments = commentQueryRepository.findAll(new Page(skip, limit), new CommentSortProvider(sort, order));
        return comments.stream().map(commentConverter::convertToDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto findById(String commentId) {
        return commentConverter.convertToDto(commentRepository.findById(commentId).get());
    }
}
