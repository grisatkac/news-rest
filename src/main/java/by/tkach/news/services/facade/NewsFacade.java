package by.tkach.news.services.facade;


import by.tkach.news.dto.news.response.NewsWithCommentsResponse;
import by.tkach.news.entities.Comment;
import by.tkach.news.entities.News;
import by.tkach.news.entities.User;
import by.tkach.news.entities.enums.Role;
import by.tkach.news.exception.UpdateException;
import by.tkach.news.mappers.NewsMapper;
import by.tkach.news.services.CommentService;
import by.tkach.news.services.NewsService;
import by.tkach.news.utils.AuthUserUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Component
@AllArgsConstructor
public class NewsFacade {

    private final NewsService newsService;
    private final CommentService commentService;
    private final NewsMapper newsMapper;

    @Transactional
    public News createNews(News news) {
        User authUser = AuthUserUtils.getAuthUser();

        news.setInsertedById(authUser.getId());

        return newsService.create(news);
    }

    @Transactional
    public News updateNews(News news) {
        User authUser = AuthUserUtils.getAuthUser();

        News newsToUpdate = newsService.findById(news.getId());

        if (authUser.getRole().name().equals(Role.ROLE_JOURNALIST.name())
                && !isUserOwnerOfNews(newsToUpdate, authUser.getId())) {
            throw new UpdateException("Journalist can only update his own news");
        }

        newsToUpdate.setTitle(news.getTitle());
        newsToUpdate.setText(news.getText());
        newsToUpdate.setUpdatedById(authUser.getId());
        return newsService.update(newsToUpdate);
    }

    @Transactional
    public News deleteNews(Long newsId) {
        User authUser = AuthUserUtils.getAuthUser();

        News newsToDelete = newsService.findById(newsId);

        if (authUser.getRole().name().equals(Role.ROLE_JOURNALIST.name())
                && !isUserOwnerOfNews(newsToDelete, authUser.getId())) {
            throw new UpdateException("Journalist can only update his own news");
        }

        return newsService.delete(newsToDelete);
    }

    @Transactional
    public NewsWithCommentsResponse findNewsWithCommentsPaginated(Long newsId, Pageable paginatedComments) {
        News news = newsService.findById(newsId);

        Page<Comment> newsComments = commentService.findAllByIdNewsPaginated(newsId, paginatedComments);

        return NewsWithCommentsResponse.builder()
                .news(newsMapper.toNewsResponse(news))
                .comments(newsComments)
                .build();
    }

    private boolean isUserOwnerOfNews(News news, Long userId) {
        return Objects.equals(news.getInsertedById(), userId);
    }
}
