package by.poshelyuk.blog.controller;

import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    //+
    @PostMapping("/articles")
    public ResponseEntity<Article> addArticle(@RequestBody Article article) {
        articleService.addArticle(article);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //+
    @GetMapping(path = "/articles")
    public ResponseEntity<List<Article>> getPublicArticles() {
        List<Article> articles = articleService.getPublicArticle();
        if (articles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        //  System.out.println(articles);
        return ResponseEntity.status(HttpStatus.OK).body(articles);
    }

    @GetMapping("/my")
    public ResponseEntity<List<Article>> getArticlesByUser() {
        //TODO after adding spring sec.
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        //TODO after adding spring sec.
        return null;
    }

    @GetMapping(path = "/filter")
    public ResponseEntity<List<Article>> getSortedArticles(
            @RequestParam(name = "skip", required = false, defaultValue = "1") Integer skip,
            @RequestParam(name = "limit", required = false, defaultValue = "3") Integer limit,
            @RequestParam(name = "sort", required = false) String sort,
            @RequestParam(name = "order", required = false) String order

    ) {
        List<Article> articles = articleService.findAll(skip, limit, sort, order);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
}
