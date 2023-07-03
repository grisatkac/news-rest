package by.tkach.news.services;


import by.tkach.news.entities.News;
import by.tkach.news.exception.NotFoundException;
import by.tkach.news.repositories.NewsRepository;
import by.tkach.news.services.impl.NewsServiceImpl;
import org.junit.jupiter.api.Assertions;
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
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;




@ExtendWith({MockitoExtension.class})
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class NewsServiceTest {

    @Mock
    private NewsRepository newsRepository;

    @InjectMocks
    private NewsServiceImpl newsService;

    private static News news;
    private static Page<News> page;
    private static PageRequest pageRequest;

    @BeforeAll
    static void init() {
        news = News.builder()
                .id(1L)
                .title("title")
                .text("text")
                .creationDate(LocalDate.now())
                .lastEditDate(null)
                .insertedById(null)
                .updatedById(null)
                .build();

        page = new PageImpl<>(new ArrayList<>(Arrays.asList(news)));
        pageRequest = PageRequest.of(0, 5);

    }

    @Test
    void findByIdTest() {
        News exceptNews = News.builder()
                .id(1L)
                .title("News title")
                .text("News text")
                .creationDate(LocalDate.of(2023, 3, 15))
                .lastEditDate(LocalDate.of(2023, 3, 15))
                .insertedById(null)
                .updatedById(null)
                .build();
        when(newsRepository.findById(exceptNews.getId())).thenReturn(Optional.ofNullable(exceptNews));

        News foundNews = newsService.findById(1L);

        assertEquals(exceptNews, foundNews);
    }

    @Test
    void findByIdThrowExceptionTest() {
        Long notExistId = 1L;
        when(newsRepository.findById(notExistId)).thenReturn(Optional.empty());

        try {
            newsService.findById(notExistId);

            Assertions.fail("Expected throwing NotFoundException exception");

        } catch (NotFoundException e) {
            assertEquals(String.format("News not exist with id: %s", notExistId), e.getMessage());
        }
    }

    @Test
    void existNewsTest() {
        when(newsRepository.existsById(news.getId())).thenReturn(true);

        boolean expected = newsService.existNews(news.getId());

        assertTrue(expected);
    }

    @Test
    void createTest() {
        News newsToCreate = News.builder()
                        .title("title")
                        .text("text")
                        .build();

        News expected = News.builder()
                        .title("title")
                        .text("text")
                        .creationDate(LocalDate.now())
                        .build();


        when(newsService.create(newsToCreate)).thenReturn(expected);

        News actual = newsService.create(newsToCreate);

        assertEquals(expected, actual);
    }

    @Test
    void updateTest() {
        News newsToUpdate = News.builder()
                .id(1L)
                .title("title")
                .text("text updated")
                .creationDate(LocalDate.now())
                .build();

        News expected = News.builder()
                .id(1L)
                .title("title")
                .text("text updated")
                .creationDate(LocalDate.now())
                .lastEditDate(LocalDate.now())
                .build();

        when(newsRepository.save(newsToUpdate)).thenReturn(expected);

        News actual = newsService.update(newsToUpdate);

        assertEquals(expected.getText(), actual.getText());
    }

    @Test
    void deleteTest() {
        News actual = newsService.delete(news);

        assertEquals(news, actual);
    }

    @Test
    void findAllPaginatedTest() {
        when(newsRepository.findAll(pageRequest)).thenReturn(page);

        Page<News> actual = newsService.findAllPaginated(pageRequest);

        assertEquals(page.getContent(), actual.getContent());
    }

    @Test
    void findByTitleAndTextTest() {
        String searchText = "text";
        News secondNews = News.builder()
                .id(2L)
                .title("text")
                .text("test")
                .build();
        page = new PageImpl<>(new ArrayList<>(Arrays.asList(news, secondNews)));
        when(newsRepository.findByTitleContainingOrTextContaining(searchText, searchText, pageRequest))
                .thenReturn(page);

        Page<News> actual = newsService.findByTitleAndText(pageRequest, searchText);

        assertEquals(page.getContent(), actual.getContent());
    }
}
