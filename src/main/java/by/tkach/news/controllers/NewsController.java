package by.tkach.news.controllers;

import by.tkach.news.dto.news.request.NewsCreateRequest;
import by.tkach.news.dto.news.request.NewsUpdateRequest;
import by.tkach.news.dto.news.response.NewsResponse;
import by.tkach.news.dto.news.response.NewsWithCommentsResponse;
import by.tkach.news.entities.News;
import by.tkach.news.mappers.NewsMapper;
import by.tkach.news.services.NewsService;
import by.tkach.news.services.facade.NewsFacade;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Class news controller handles all request whose request url path starts with 'news'.
 * @author Grisha Tkach
 * @version 1.0.0
 */
@RestController
@AllArgsConstructor
@RequestMapping("/news")
public class NewsController {

    /** Field contains logic for interacting with news. */
    private final NewsService newsService;

    /** Field contains logic for interacting with news, which consists of many simple operations. */
    private final NewsFacade newsFacade;

    /** Field contains methods for type conversion. */
    private final NewsMapper newsMapper;

    /**
     * Create new news {@link News}. News can create user with Admin, Journalist roles.
     * @param newsCreateRequest {@link NewsCreateRequest} - must be not null.
     * @return {@link NewsResponse}
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_JOURNALIST') or hasRole('ROLE_ADMIN')")
    public NewsResponse create(@Valid @RequestBody NewsCreateRequest newsCreateRequest) {
        News createdNews = newsFacade.createNews(newsMapper.toNews(newsCreateRequest));
        return newsMapper.toNewsResponse(createdNews);
    }

    /**
     * Returns news with the given identifier and comments that belong to this news.
     * If a news with the provided identifier does not exist, an error will be thrown
     * {@link by.tkach.news.exception.NotFoundException} exception.
     * This operation can be performed with any role and unauthorized.
     * @param id - must belong to an existing news.
     * @return {@link NewsResponse}
     */
    @GetMapping("/{id}")
    public NewsWithCommentsResponse getById(@PathVariable Long id, @PageableDefault(size = 5, sort = "creationDate", direction = Sort.Direction.DESC) Pageable paginatedComments) {
        return newsFacade.findNewsWithCommentsPaginated(id, paginatedComments);
    }

    /**
     * Returns a sublist of a list of comments for the given {@link Pageable}.
     * This operation can be performed with any role and unauthorized
     * @param pageable - must be not null.
     * @return {@link Page} of {@link by.tkach.news.entities.Comment} - sublist of a list of comments
     */
    @GetMapping("/all")
    public Page<NewsResponse> getAll(@PageableDefault(size = 5, sort = "creationDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return newsService.findAllPaginated(pageable).map(newsMapper::toNewsResponse);
    }

    /**
     * Returns a sublist of a list of news for the given {@link Pageable}.
     * This operation can be performed with any role and unauthorized
     * @param pageable - must be not null.
     * @return {@link Page} of {@link News} - sublist of a list of news
     */
    @GetMapping("/search")
    public Page<NewsResponse> getNewsByTitleAndText(@PageableDefault(size = 5, sort = "creationDate", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam String searchText) {
        return newsService.findByTitleAndText(pageable, searchText).map(newsMapper::toNewsResponse);
    }

    /**
     * Receive {@link NewsUpdateRequest} object to update news. News can update user with Admin, Journalist roles.
     * @param newsUpdateRequest {@link NewsResponse} - object to update exist news, must be not null.
     * @return {@link NewsResponse} - updated news.
     */
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_JOURNALIST') or hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public NewsResponse update(@Valid @RequestBody NewsUpdateRequest newsUpdateRequest) {
        News updatedNews = newsFacade.updateNews(newsMapper.toNews(newsUpdateRequest));
        return newsMapper.toNewsResponse(updatedNews);
    }

    /**
     * Delete news and comments that belong to this news with the given identifier. If a news with the provided identifier does not exist, an error will be thrown {@link by.tkach.news.exception.NotFoundException} exception.
     * @param id - news id to delete. News can delete user with Admin, Journalist roles. Return deleted news.
     * @return {@link NewsResponse} - deleted comment.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_JOURNALIST') or hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public NewsResponse deleteById(@PathVariable Long id) {
        News deletedNews = newsFacade.deleteNews(id);
        return newsMapper.toNewsResponse(deletedNews);
    }
}
