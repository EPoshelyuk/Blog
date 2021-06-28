package by.poshelyuk.blog.controller;

import by.poshelyuk.blog.dto.CommentDto;
import by.poshelyuk.blog.entity.User;
import by.poshelyuk.blog.exception.ArticleNotFoundException;
import by.poshelyuk.blog.service.ArticleService;
import by.poshelyuk.blog.service.CommentService;
import by.poshelyuk.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;
    private final ArticleService articleService;

    @Autowired
    public CommentController(CommentService commentService, UserService userService, ArticleService articleService) {
        this.commentService = commentService;
        this.userService = userService;
        this.articleService = articleService;
    }

    @PostMapping("/articles/{id}/comments")
    public ResponseEntity<String> addComment(@PathVariable String id, @Valid @RequestBody CommentDto commentDto, Authentication authentication) throws ArticleNotFoundException {

        User user = userService.findByEmail((String) authentication.getPrincipal());
        commentDto.setUser(user);
        commentDto.setArticle(articleService.findById(id));
        commentService.addComment(commentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/articles/{id}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByArticleId(@PathVariable String id) {
        List<CommentDto> commentDtos = commentService.findByArticleId(id);
        if (CollectionUtils.isEmpty(commentDtos)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(commentDtos, HttpStatus.OK);
    }

    @GetMapping("/articles/{articleId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentByIdAndArticleId(@PathVariable String articleId, @PathVariable String commentId) {
        CommentDto commentDto = commentService.getCommentByIdAndArticleId(articleId, commentId);
        if (commentDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @GetMapping(path = "/comments/filter")
    public ResponseEntity<List<CommentDto>> getSortedComments(
            @RequestParam(name = "skip", required = false, defaultValue = "1") Integer skip,
            @RequestParam(name = "limit", required = false, defaultValue = "5") Integer limit,
            @RequestParam(name = "sort", required = false) String sort,
            @RequestParam(name = "order", required = false) String order) {
        List<CommentDto> commentDtos = commentService.findAll(skip, limit, sort, order);
        return new ResponseEntity<>(commentDtos, HttpStatus.OK);
    }
}
