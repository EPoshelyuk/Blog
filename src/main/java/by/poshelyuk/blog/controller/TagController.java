package by.poshelyuk.blog.controller;

import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/articles")
    public ResponseEntity<List<Article>> getArticlesByTags(@RequestParam List<String> tagNames) {
        List<Article> articles = tagService.getArticlesByTags(tagNames);
        if (articles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(articles, HttpStatus.OK);
    }

    @GetMapping("/cloud")
    public ResponseEntity<Map<String, Integer>> getTagsCloud() {
        Map<String, Integer> tagCloud = tagService.getTagCloud();
        if (tagCloud.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(tagCloud, HttpStatus.OK);
    }
}


