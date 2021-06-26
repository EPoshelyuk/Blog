package by.poshelyuk.blog.dto;

import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.entity.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Date;


@Data
public class CommentDto {

    private String commentId;

    @NotBlank
    private String message;

    private Article article;

    private User user;

    private Date createdAt;
}
