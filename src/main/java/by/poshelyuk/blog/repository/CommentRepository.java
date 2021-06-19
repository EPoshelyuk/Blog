package by.poshelyuk.blog.repository;


import by.poshelyuk.blog.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {

    Optional<Comment> findById(String id);

    @Query("SELECT c FROM Comment c WHERE c.article.articleId=?1")
    List<Comment> findByArticleId(String id);

    @Query("SELECT c FROM Comment c WHERE c.article.articleId=?1 AND c.commentId=?2")
    Comment getCommentByIdAndArticleId(String articleId, String commentId);

}

