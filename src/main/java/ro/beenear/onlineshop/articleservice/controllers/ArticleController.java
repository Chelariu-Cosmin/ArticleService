package ro.beenear.onlineshop.articleservice.controllers;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ro.beenear.onlineshop.articleservice.beans.ArticleDto;
import ro.beenear.onlineshop.articleservice.services.ArticleService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * @return all entities response status 200 OK,
     * If the list is empty but everything works fine, will return also 200 OK
     * 500 InternalServer Error if the server has encountered
     * an internal error and was unable to complete the request.
     */
    @GetMapping("/")
    public ResponseEntity<List<ArticleDto>> findAll() {

        try {
            return new ResponseEntity<>(articleService.findAll(), HttpStatus.OK);
        } catch (Throwable throwable) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage());
        }
    }

    /**
     * @param id cannot be null
     * @return articleDto and 200 OK response status if the id from an entity exists,
     * otherwise will return 404 Not Found,
     * Return 400 Bad Request if the server will
     * not process with an invalid path variable
     * Return 500 Internal Server Error, this indicates the server has encountered
     * an internal error and was unable to complete the request.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> findById(@PathVariable("id") @NotNull Long id) {

        try {
            return new ResponseEntity<>(articleService.findArticleById(id), HttpStatus.OK);
        } catch (InvalidDataAccessApiUsageException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (Throwable ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    /**
     * Saves a given article.
     *
     * @param articleDto - must not be null.
     * @return the saved entity and 201 Created response status
     * when the request succeeded,
     * BAD_REQUEST (400) and INTERNAL_SERVER_ERROR (500) otherwise.
     * response status 500 when the server has encountered
     * an internal error and was unable to complete the request.
     * 400 response status if the server will
     * not process with an invalid object
     */
    @PostMapping
    public ResponseEntity<Long> create(@Valid @RequestBody ArticleDto articleDto) {

        try {
            return new ResponseEntity<>(articleService.save(articleDto), HttpStatus.CREATED);
        } catch (Throwable throwable) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage());
        }
    }

    /**
     * Update entity with the given ID
     *
     * @param id cannot be null
     * @param articleDto - provided data
     * @return id and 200 status ok if the entity with a given id already exists
     * null, 404 Not Found otherwise.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@PathVariable("id") @NotNull Long id, @Valid @RequestBody ArticleDto
            articleDto) {

        try {
            return new ResponseEntity<>(articleService.update(id, articleDto), HttpStatus.OK);
        } catch (InvalidDataAccessApiUsageException invalidDataAccessApiUsageException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, invalidDataAccessApiUsageException.getMessage());
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        } catch (Throwable exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    /**
     * Deletes the entity with the given id.
     * Return 400 Bad Request if the server will not process the request due to
     * invalid path variable.
     * if the path variable is deleted return status 200 ok
     * otherwise
     * return status 500 if the server encountered a situation it cannot handle
     *
     * @param id cannot be null
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") @NotNull Long id) {

        articleService.deleteById(id);

        try {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (InvalidDataAccessApiUsageException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        } catch (Throwable throwable) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage());
        }
    }
}