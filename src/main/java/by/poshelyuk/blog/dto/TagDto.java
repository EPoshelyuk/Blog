package by.poshelyuk.blog.dto;

import by.poshelyuk.blog.entity.Article;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class TagDto {

    private String tagId;

    @NotBlank
    private String name;

    @JsonIgnore
    @ToString.Exclude
    private List<Article> articles;
}
