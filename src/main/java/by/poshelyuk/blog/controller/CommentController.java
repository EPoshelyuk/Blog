package by.poshelyuk.blog.controller;

import by.poshelyuk.blog.entity.Comment;
import by.poshelyuk.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;

    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<String> addComment(@PathVariable String id) {
        //TODO
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{id}/comments")
    public ResponseEntity<List<Comment>> getCommentsByArticleId(@PathVariable String id) {
        List<Comment> comments = commentService.getByArticleId(id);
        if (CollectionUtils.isEmpty(comments)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    //GET /articles/:id/comments/:id - просмотр комментария

    @GetMapping("{articleId}/comments/{commentId}")
    public ResponseEntity<Comment> getCommentByIdAndArticleId(@PathVariable String articleId, @PathVariable String commentId) {
        Comment comment = commentService.getCommentByIdAndArticleId(articleId, commentId);
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    //DELETE /articles/:id/comments/:id - удалить, удалить может только автор комментария или поста

    @DeleteMapping("{articleId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable String articleId, @PathVariable String commentId) {
        //TODO
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
