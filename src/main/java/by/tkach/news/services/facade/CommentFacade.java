package by.tkach.news.services.facade;

import by.tkach.news.entities.Comment;
import by.tkach.news.entities.User;
import by.tkach.news.entities.enums.Role;
import by.tkach.news.exception.CreateException;
import by.tkach.news.exception.UpdateException;
import by.tkach.news.services.CommentService;
import by.tkach.news.services.NewsService;
import by.tkach.news.utils.AuthUserUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Component
@AllArgsConstructor
public class CommentFacade {

    private final CommentService commentService;
    private final NewsService newsService;

    @Transactional
    public Comment createComment(Comment comment) {
        User authUser = AuthUserUtils.getAuthUser();

        if (!newsService.existNews(comment.getIdNews())) {
            throw new CreateException(String.format("News not exist with id: %s", comment.getIdNews()));
        }

        comment.setInsertedById(authUser.getId());
        return commentService.create(comment);
    }

    @Transactional
    public Comment updateComment(Comment comment) {
        User authUser = AuthUserUtils.getAuthUser();

        Comment commentToUpdate = commentService.findById(comment.getId());

        if (authUser.getRole().name().equals(Role.ROLE_SUBSCRIBER.name())
                && !isUserOwnerOfComment(commentToUpdate, authUser.getId())) {
            throw new UpdateException("Subscriber can only update his own comments");
        }

        commentToUpdate.setText(comment.getText());
        return commentService.update(commentToUpdate);
    }

    @Transactional
    public Comment deleteComment(Long commentId) {
        User authUser = AuthUserUtils.getAuthUser();

        Comment commentToDelete = commentService.findById(commentId);

        if (authUser.getRole().name().equals(Role.ROLE_SUBSCRIBER.name())
                && !isUserOwnerOfComment(commentToDelete, authUser.getId())) {
            throw new UpdateException("Subscriber can only delete his own comments");
        }

        return commentService.delete(commentToDelete);
    }

    private boolean isUserOwnerOfComment(Comment comment, Long userId) {
        return Objects.equals(comment.getInsertedById(), userId);
    }
}
