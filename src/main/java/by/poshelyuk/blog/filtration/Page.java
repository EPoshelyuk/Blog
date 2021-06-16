package by.poshelyuk.blog.filtration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Page {
    private Integer skip;
    private Integer limit;
}
