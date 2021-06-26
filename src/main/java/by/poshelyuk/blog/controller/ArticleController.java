package by.poshelyuk.blog.controller;

import by.poshelyuk.blog.dto.ArticleDto;
import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.entity.User;
import by.poshelyuk.blog.exception.ArticleNotFoundException;
import by.poshelyuk.blog.service.ArticleService;
import by.poshelyuk.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ArticleController {

    private final ArticleService articleService;
    private final UserService userService;

    @Autowired
    public ArticleController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @PutMapping("/articles/{id}")
    public ResponseEntity<String> update(@PathVariable String id, @Valid @RequestBody ArticleDto articleDto, Authentication authentication){

        User userByAuth = userService.findByEmail((String) authentication.getPrincipal());
        User userById = userService.findById(id);
        if (!userByAuth.equals(userById)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Article articleFromDB = articleService.findById(id);
        if (articleFromDB == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            articleService.update(articleDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PostMapping("/articles")
    public ResponseEntity<ArticleDto> addArticle(@Valid @RequestBody ArticleDto articleDto) {
        articleService.addArticle(articleDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/articles")
    public ResponseEntity<List<ArticleDto>> getPublicArticles() {
        List<ArticleDto> articlesDto = articleService.getPublicArticle();
        if (articlesDto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(articlesDto);
    }

    @GetMapping("/my")
    public ResponseEntity<List<ArticleDto>> getArticlesByUser(Authentication authentication) {

        String userEmail = authentication.getPrincipal().toString();
        List<ArticleDto> articlesDto = articleService.findAllByUserEmail(userEmail);
        if (articlesDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(articlesDto, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id, Authentication authentication) throws ArticleNotFoundException {

        Article article = articleService.findById(id);
        User user = userService.findByEmail(authentication.getName());
        if (article.getUser().getEmail() != authentication.getPrincipal()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        articleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/filter")
    public ResponseEntity<List<ArticleDto>> getSortedArticles(
            @RequestParam(name = "skip", required = false, defaultValue = "1") Integer skip,
            @RequestParam(name = "limit", required = false, defaultValue = "3") Integer limit,
            @RequestParam(name = "sort", required = false) String sort,
            @RequestParam(name = "order", required = false) String order) {
        List<ArticleDto> articlesDto = articleService.findAll(skip, limit, sort, order);
        return new ResponseEntity<>(articlesDto, HttpStatus.OK);
    }
}
