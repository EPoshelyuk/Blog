package by.poshelyuk.blog.exception;

import javassist.NotFoundException;

public class ArticleNotFoundException extends NotFoundException {

    public ArticleNotFoundException(String id) {
        super("Article is not found with id :" + id);
    }
}
