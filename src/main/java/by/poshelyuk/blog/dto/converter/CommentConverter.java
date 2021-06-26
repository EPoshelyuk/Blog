package by.poshelyuk.blog.dto.converter;

import by.poshelyuk.blog.dto.CommentDto;
import by.poshelyuk.blog.entity.Comment;
import org.springframework.stereotype.Service;

@Service
public class CommentConverter {
    public Comment convertToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setUser(commentDto.getUser());
        comment.setMessage(commentDto.getMessage());
        comment.setArticle(commentDto.getArticle());
        comment.setCreatedAt(commentDto.getCreatedAt());
        comment.setCommentId(commentDto.getCommentId());
        return comment;
    }

    public CommentDto convertToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setArticle(comment.getArticle());
        commentDto.setMessage(comment.getMessage());
        commentDto.setCommentId(comment.getCommentId());
        commentDto.setUser(comment.getUser());
        commentDto.setCreatedAt(comment.getCreatedAt());
        return commentDto;
    }
}
