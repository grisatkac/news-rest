package by.tkach.news.services;

import by.tkach.news.entities.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<T extends AbstractEntity> {

    T create(T entity);

    T findById(Long id);

    Page<T> findAllPaginated(Pageable pageable);

    T update(T entity);

    T delete(T entity);
}
