package by.poshelyuk.blog.dto;

import by.poshelyuk.blog.entity.Tag;
import by.poshelyuk.blog.entity.User;
import by.poshelyuk.blog.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.List;

@Data
public class ArticleDto {
    private String articleId;

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    private Status status;

    @NotBlank
    private User user;

    private Date createdAt;

    private Date updatedAt;

    @JsonIgnore
    @ToString.Exclude
    private List<Tag> tags;
}
