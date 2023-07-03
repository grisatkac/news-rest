package by.tkach.news.services;


import by.tkach.news.entities.User;
import by.tkach.news.entities.enums.Role;
import by.tkach.news.repositories.UserRepository;
import by.tkach.news.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;



@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private static String password;
    private static User user;

    @BeforeAll
    static void init() {
        password = "$2a$10$eOFU9P2HsjLf6/OaxvTnpOccjouzllRk1FXJYNyZ3UEeRvo21pMCS";
        user = User.builder()
                .id(1L)
                .userName("userName")
                .role(Role.ROLE_ADMIN)
                .password(password)
                .name("name")
                .surname("surname")
                .parentName("parentName")
                .creationDate(LocalDate.now())
                .build();
    }

    @Test
    void createTest() {
        User userToCreate = User.builder()
                .id(1L)
                .userName("userName")
                .role(Role.ROLE_ADMIN)
                .name("name")
                .surname("surname")
                .parentName("parentName")
                .build();
        when(userRepository.save(userToCreate)).thenReturn(user);

        User actual = userService.create(userToCreate);

        assertEquals(user, actual);
    }

    @Test
    void findByUserNameTest() {
        when(userRepository.findByUserName(user.getUserName())).thenReturn(user);

        User actual = userService.findByUserName(user.getUserName());

        assertEquals(user, actual);
    }

    @Test
    void findByUserNameAndPasswordTest() {
        when(userRepository.findByUserName(user.getUserName())).thenReturn(user);
        when(passwordEncoder.matches(password, user.getPassword())).thenReturn(true);

        User actual = userService.findByUserNameAndPassword(user.getUserName(), user.getPassword());

        assertEquals(user, actual);
    }
}
