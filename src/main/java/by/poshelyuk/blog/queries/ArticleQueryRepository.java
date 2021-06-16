package by.poshelyuk.blog.queries;

import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.filtration.Page;
import by.poshelyuk.blog.filtration.impl.ArticleSortProvider;

import java.util.List;

public interface ArticleQueryRepository {

    List<Article> findAll(Page page, ArticleSortProvider articleSortProvider);
}
