package by.poshelyuk.blog.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TAGS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tag {

    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    @Column(name = "TAG_ID")
    private String tagId;

    @Column(name = "NAME")
    private String name;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(mappedBy = "tags" , fetch = FetchType.EAGER)
    private List<Article> articles;
}

