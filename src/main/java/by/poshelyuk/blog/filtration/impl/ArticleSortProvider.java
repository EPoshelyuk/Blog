package by.poshelyuk.blog.filtration.impl;

import by.poshelyuk.blog.entity.Article;
import by.poshelyuk.blog.filtration.SortOrder;
import by.poshelyuk.blog.filtration.SortProvider;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

@Getter
public class ArticleSortProvider implements SortProvider<Article> {

    private final String sortBy;
    private final SortOrder sortOrder;

    public ArticleSortProvider(String sortBy, String sortOrder) {
        this.sortBy = sortBy;
        this.sortOrder = sortOrder == null ? null : SortOrder.valueOf(sortOrder.toUpperCase());
    }

    @Override
    public Order[] getSortOrder(Root<Article> root, CriteriaBuilder criteriaBuilder) {
        if (ObjectUtils.isEmpty(sortBy) && ObjectUtils.isEmpty(sortOrder)) {
            return new Order[]{};
        }
        if (sortOrder == SortOrder.DESC) {
            return new Order[]{criteriaBuilder.desc(root.get(sortBy))};
        }
        return new Order[]{criteriaBuilder.asc(root.get(sortBy))};
    }

}
