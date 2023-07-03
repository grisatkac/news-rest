package by.tkach.news.repositories;

import by.tkach.news.entities.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbstractRepository <T extends AbstractEntity> extends JpaRepository<T, Long> {
}
