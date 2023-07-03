package by.tkach.news.repositories;

import by.tkach.news.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends AbstractRepository<User> {

    User findByUserName(String userName);

    boolean existsByUserName(String userName);

    boolean existsByNameAndSurnameAndParentName(String name, String surname, String parentName);
}
