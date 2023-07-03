package by.tkach.news.services;

import by.tkach.news.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CommentService extends CrudService<Comment> {

    Page<Comment> findAllByIdNewsPaginated(Long newsId, Pageable pageable);

    Comment findByCommentIdAndInsertedById(Long commentId, Long insertedById);
}
