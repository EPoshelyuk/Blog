package by.poshelyuk.blog.repository;



import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {

     Tag findByName(String name);

}
