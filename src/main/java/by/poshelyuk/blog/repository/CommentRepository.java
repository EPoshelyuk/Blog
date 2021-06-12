package by.poshelyuk.blog.repository;


import by.poshelyuk.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {

    List<Comment> findCommentByArticleId(String id);

    Comment findByCommentIdAndArticle_ArticleId(String commentId, String articleId);

}
