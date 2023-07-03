package by.tkach.news.repositories;

import by.tkach.news.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends AbstractRepository<Comment> {

    Page<Comment> findAllByIdNews(Long id, Pageable pageable);

    Comment findByIdAndInsertedById(Long id, Long insertedById);

    boolean existsByIdAndInsertedById(Long commentId, Long insertedById);
}
