package ro.beenear.onlineshop.articleservice.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import ro.beenear.onlineshop.articleservice.beans.CategoryTypeDto;
import ro.beenear.onlineshop.articleservice.beans.UnitOfMeasureDto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@NoArgsConstructor
@Data
@Entity
@Table(name = "articles")

public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    private String description;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "Category_Type")
    private CategoryTypeDto categoryTypeDto;

    @Enumerated(EnumType.STRING)
    @Column(name = "Unit_Of_Measure")
    private UnitOfMeasureDto unitOfMeasureDto;
    @Column(name = "URL")
    private String url;

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }

        Article article = (Article) o;
        return id != null && Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
