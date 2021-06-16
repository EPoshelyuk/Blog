package by.poshelyuk.blog.queries;

import by.poshelyuk.blog.entity.Comment;
import by.poshelyuk.blog.filtration.Page;
import by.poshelyuk.blog.filtration.impl.CommentSortProvider;

import java.util.List;

public interface CommentQueryRepository {

    List<Comment> findAll(Page page, CommentSortProvider commentSortProvider);
}
