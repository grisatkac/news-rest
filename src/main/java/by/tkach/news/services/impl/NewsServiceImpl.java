package by.tkach.news.services.impl;

import by.tkach.news.entities.News;
import by.tkach.news.exception.NotFoundException;
import by.tkach.news.repositories.NewsRepository;
import by.tkach.news.services.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Transactional
    @Override
    public News create(News news) {
        news.setCreationDate(LocalDate.now());
        return newsRepository.save(news);
    }

    @Override
    @Transactional(readOnly = true)
    public News findById(Long id) {
        return newsRepository.findById(id)
               .orElseThrow(() -> new NotFoundException(String.format("News not exist with id: %s", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<News> findByTitleAndText(Pageable pageable, String searchText) {
        return newsRepository.findByTitleContainingOrTextContaining(searchText, searchText, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<News> findAllPaginated(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existNews(Long id) {
        return newsRepository.existsById(id);
    }

    @Transactional
    @Override
    public News update(News news) {
        news.setLastEditDate(LocalDate.now());
        return newsRepository.save(news);
    }

    @Transactional
    @Override
    public News delete(News news) {
        newsRepository.delete(news);
        return news;
    }
}
