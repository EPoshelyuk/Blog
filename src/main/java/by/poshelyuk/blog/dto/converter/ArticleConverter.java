package by.poshelyuk.blog.dto.converter;

import by.poshelyuk.blog.dto.ArticleDto;
import by.poshelyuk.blog.entity.Article;
import org.springframework.stereotype.Component;

@Component
public class ArticleConverter {
    public Article convertToEntity(ArticleDto articleDto) {
        Article article = new Article();
        article.setArticleId(articleDto.getArticleId());
        article.setText(articleDto.getText());
        article.setTitle(articleDto.getTitle());
        article.setStatus(articleDto.getStatus());
        article.setUpdatedAt(articleDto.getUpdatedAt());
        article.setCreatedAt(articleDto.getCreatedAt());
        article.setUser(articleDto.getUser());
        article.setTags(articleDto.getTags());
        return article;
    }

    public ArticleDto convertToDto(Article article) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setArticleId(article.getArticleId());
        articleDto.setStatus(article.getStatus());
        articleDto.setUser(article.getUser());
        articleDto.setText(article.getText());
        articleDto.setTitle(article.getTitle());
        articleDto.setCreatedAt(article.getCreatedAt());
        articleDto.setUpdatedAt(article.getUpdatedAt());
        articleDto.setTags(article.getTags());
        return articleDto;
    }
}
