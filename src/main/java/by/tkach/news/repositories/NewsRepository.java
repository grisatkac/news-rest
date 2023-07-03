package by.tkach.news.repositories;

import by.tkach.news.entities.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends AbstractRepository<News> {

    Page<News> findByTitleContainingOrTextContaining(String searchText, String searchTextForText, Pageable pageable);

    News findByIdAndInsertedById(Long id, Long insertedById);

    boolean existsByIdAndInsertedById(Long newsId, Long insertedById);
}
