package ro.beenear.onlineshop.articleservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.beenear.onlineshop.articleservice.entities.Article;

public interface ArticlesRepository extends JpaRepository<Article, Long> {


}
