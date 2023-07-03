package by.tkach.news.services;

import by.tkach.news.entities.Comment;
import by.tkach.news.repositories.CommentRepository;
import by.tkach.news.services.impl.CommentServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    private static Comment comment;
    private static Page<Comment> page;
    private static PageRequest pageRequest;

    @BeforeAll
    static void init() {
        comment = Comment.builder()
                .id(1L)
                .text("text")
                .creationDate(LocalDate.now())
                .idNews(1L)
                .build();

        page = new PageImpl<>(new ArrayList<>(List.of(comment)));
        pageRequest = PageRequest.of(0, 5);
    }

    @Test
    void createTest() {
        when(commentRepository.save(comment)).thenReturn(comment);

        Comment actual = commentService.create(comment);

        assertEquals(comment, actual);
    }

    @Test
    void findByIdTest() {
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.of(comment));

        Comment actual = commentService.findById(comment.getId());

        assertEquals(comment, actual);
    }

    @Test
    void updateTest() {
        Comment expected = Comment.builder()
                .id(1L)
                .text("text")
                .creationDate(LocalDate.now())
                .lastEditDate(LocalDate.now())
                .idNews(1L)
                .build();

        when(commentRepository.save(comment)).thenReturn(expected);

        Comment actual = commentService.update(comment);

        assertEquals(expected, actual);
    }

    @Test
    void deleteTest() {
        Comment actual = commentService.delete(comment);

        assertEquals(comment, actual);
    }

    @Test
    void findAllPaginatedTest() {
        when(commentRepository.findAll(pageRequest)).thenReturn(page);

        Page<Comment> actual = commentService.findAllPaginated(pageRequest);

        assertEquals(page.getContent(), actual.getContent());
    }

    @Test
    void findAllByIdNewsPaginatedTest() {
        when(commentRepository.findAllByIdNews(comment.getIdNews(), pageRequest)).thenReturn(page);

        Page<Comment> actual = commentService.findAllByIdNewsPaginated(comment.getId(), pageRequest);

        assertEquals(page.getContent(), actual.getContent());
    }

    @Test
    void findByCommentIdAndInsertedByIdTest() {
        Long commentId = 1L;
        Long insertedById = 1L;

        Comment expected = Comment.builder()
                .id(commentId)
                .text("text")
                .creationDate(LocalDate.now())
                .insertedById(insertedById)
                .idNews(1L)
                .build();

        when(commentRepository.findByIdAndInsertedById(commentId, insertedById)).thenReturn(expected);

        Comment actual = commentService.findByCommentIdAndInsertedById(commentId, insertedById);

        assertEquals(expected, actual);
    }
}
