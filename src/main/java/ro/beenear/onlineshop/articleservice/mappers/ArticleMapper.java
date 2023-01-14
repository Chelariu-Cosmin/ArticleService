package ro.beenear.onlineshop.articleservice.mappers;

import org.mapstruct.Mapper;
import ro.beenear.onlineshop.articleservice.beans.ArticleDto;
import ro.beenear.onlineshop.articleservice.entities.Article;

@Mapper
public interface ArticleMapper {

    ArticleDto toDto(Article article);

    Article toArticle(ArticleDto articleDto);
}
