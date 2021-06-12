package by.poshelyuk.blog.controller;

import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PutMapping("/articles/{id}")
    public ResponseEntity<String> update(@PathVariable String id) {
        //TODO after adding spring sec.
        return null;
    }

    @PostMapping("/articles")
    public ResponseEntity<Article> addArticle(@RequestBody Article article) {
        articleService.addArticle(article);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/articles", produces="application/json")
    public ResponseEntity<List<Article>> getPublicArticles() {
        List<Article> articles = articleService.getPublicArticle();
        if (CollectionUtils.isEmpty(articles)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Article>> getAll() {
        List<Article> articles = articleService.getAll();
        if (CollectionUtils.isEmpty(articles)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/my")
    public ResponseEntity<List<Article>> getArticlesByUser() {
        //TODO after adding spring sec.
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        //TODO after adding spring sec.
        return null;
    }

}