package ro.beenear.onlineshop.articleservice.services;

import ro.beenear.onlineshop.articleservice.beans.ArticleDto;

import java.util.List;

public interface ArticleService {

    List<ArticleDto> findAll();

    Long save(ArticleDto articleDto);

    Long update(Long id, ArticleDto articleDto);

    void deleteById(Long id);

    ArticleDto findArticleById(Long id);
}
