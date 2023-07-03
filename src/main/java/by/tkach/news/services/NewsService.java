package by.tkach.news.services;

import by.tkach.news.entities.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NewsService extends CrudService<News> {

    boolean existNews(Long id);

    Page<News> findByTitleAndText(Pageable pageable, String searchText);
}
