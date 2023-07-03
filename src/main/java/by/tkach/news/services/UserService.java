package by.tkach.news.services;

import by.tkach.news.entities.User;

public interface UserService {

    User create(User user);

    User findByUserName(String login);

    User findByUserNameAndPassword(String userName, String password);

    boolean existsByUserName(String userName);

    boolean existByFullName(String name, String surname, String parentName);
}
