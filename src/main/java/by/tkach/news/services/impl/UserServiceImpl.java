package by.tkach.news.services.impl;


import by.tkach.news.entities.User;
import by.tkach.news.exception.NotFoundException;
import by.tkach.news.repositories.UserRepository;
import by.tkach.news.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User create(User user) {
        user.setCreationDate(LocalDate.now());
        user.setPassword(passwordEncoder.encode("password"));
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUserNameAndPassword(String userName, String password) {
        User user = findByUserName(userName);

        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        } else {
            throw new NotFoundException(String.format("User with username '%s' not exist", userName));
        }

        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existByFullName(String name, String surname, String parentName) {
        return userRepository.existsByNameAndSurnameAndParentName(name, surname, parentName);
    }
}
