package ro.beenear.onlineshop.articleservice.services;

import org.springframework.stereotype.Service;
import ro.beenear.onlineshop.articleservice.beans.ArticleDto;
import ro.beenear.onlineshop.articleservice.entities.Article;
import ro.beenear.onlineshop.articleservice.mappers.ArticleMapper;
import ro.beenear.onlineshop.articleservice.repositories.ArticlesRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements  ArticleService {

   private final ArticlesRepository articlesRepository;

   private final ArticleMapper articleMapper;


    public ArticleServiceImpl(ArticlesRepository articlesRepository, ArticleMapper articleMapper) {
        this.articlesRepository = articlesRepository;
        this.articleMapper = articleMapper;
    }

    @Override
    public List<ArticleDto> findAll() {

        return articlesRepository.findAll()
                .stream()
                .map(articleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ArticleDto findArticleById(Long id) {

        return articlesRepository.findById(id)
                .map(articleMapper::toDto)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Long save(ArticleDto articleDto){

        return articlesRepository.save(articleMapper.toArticle(articleDto))
                .getId();
    }

    @Override
    public Long update(Long id, ArticleDto articleDto) {

        Optional<Article> articleOptional = articlesRepository.findById(id);

        if (articleOptional.isEmpty())
            throw new NoSuchElementException(" Article with id " + id + " does not exist");

        return articlesRepository.save(articleMapper.toArticle(articleDto))
                .getId();
    }

    @Override
    public void deleteById(Long id) {

        articlesRepository.deleteById(id);
    }
}
