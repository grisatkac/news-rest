package by.tkach.news.services.impl;

import by.tkach.news.entities.Comment;
import by.tkach.news.exception.NotFoundException;
import by.tkach.news.repositories.CommentRepository;
import by.tkach.news.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public Comment create(Comment comment) {
        comment.setCreationDate(LocalDate.now());
        return commentRepository.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Comment not exist with id: %s", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public Comment findByCommentIdAndInsertedById(Long commentId, Long insertedById) {
        return commentRepository.findByIdAndInsertedById(commentId, insertedById);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Comment> findAllPaginated(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Comment> findAllByIdNewsPaginated(Long newsId, Pageable pageable) {
        return commentRepository.findAllByIdNews(newsId, pageable);
    }

    @Override
    @Transactional
    public Comment update(Comment comment) {
        comment.setLastEditDate(LocalDate.now());
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public Comment delete(Comment comment) {
        commentRepository.delete(comment);
        return comment;
    }
}
