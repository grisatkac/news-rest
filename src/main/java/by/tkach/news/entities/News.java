package by.tkach.news.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@SuperBuilder
@RequiredArgsConstructor
@Entity
@Table(name = "news")
public class News extends AbstractEntity {

    private String title;
    private String text;
    private LocalDate creationDate;
    private LocalDate lastEditDate;
    @Column(name = "inserted_by_id", nullable = false)
    private Long insertedById;
    @Column(name = "updated_by_id", nullable = false)
    private Long updatedById;

    @OneToMany(mappedBy = "idNews",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Comment> comments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        News task = (News) o;
        return id != null && Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
