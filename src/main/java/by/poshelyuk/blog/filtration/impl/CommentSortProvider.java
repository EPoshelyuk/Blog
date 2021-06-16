package by.poshelyuk.blog.filtration.impl;

import by.poshelyuk.blog.entity.Comment;
import by.poshelyuk.blog.filtration.SortOrder;
import by.poshelyuk.blog.filtration.SortProvider;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

public class CommentSortProvider implements SortProvider<Comment> {

    private final String sortBy;
    private final SortOrder sortOrder;

    public CommentSortProvider(String sortBy, String sortOrder) {
        this.sortBy = sortBy;
        this.sortOrder = sortOrder == null ? null : SortOrder.valueOf(sortOrder.toUpperCase());
    }

    @Override
    public Order[] getSortOrder(Root<Comment> root, CriteriaBuilder criteriaBuilder) {
        if (ObjectUtils.isEmpty(sortBy) && ObjectUtils.isEmpty(sortOrder)) {
            return new Order[]{};
        }
        if (sortOrder == SortOrder.DESC) {
            return new Order[]{criteriaBuilder.desc(root.get(sortBy))};
        }
        return new Order[]{criteriaBuilder.asc(root.get(sortBy))};
    }
}
