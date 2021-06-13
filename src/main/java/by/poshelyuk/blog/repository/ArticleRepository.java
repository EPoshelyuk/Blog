package by.poshelyuk.blog.repository;

import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.entity.Tag;
import by.poshelyuk.blog.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {

    List<Article> getAllByStatus(Status status);

    @Query(value = "SELECT a FROM Article a WHERE :tag MEMBER a.tags")
    List<Article> findAllByTag(@Param("tag") Tag tag);
}

