package by.poshelyuk.blog.repository;

import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.entity.Tag;
import by.poshelyuk.blog.entity.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {

    List<Article> getAllByStatus(Status status);

    List<Article> findAll();

    void deleteById(String id);

    Optional<Article> findById(String id);

    Page<Article> findAll(Pageable pageable);

    @Query("SELECT a FROM Article a WHERE :tag MEMBER a.tags")
    List<Article> getAllByTag(@Param("tag") Tag tag);
}


