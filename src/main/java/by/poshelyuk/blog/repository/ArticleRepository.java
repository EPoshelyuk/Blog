package by.poshelyuk.blog.repository;

import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {

    List<Article> getAllByStatus(Status status);

   // @Query("SELECT a FROM Article a INNER JOIN " )
   // List<Article> getAll();


}
