package by.tkach.news.services.impl;


import by.tkach.news.dto.auth.request.AuthRequest;
import by.tkach.news.dto.auth.response.AuthResponse;
import by.tkach.news.entities.User;
import by.tkach.news.exception.CreateException;
import by.tkach.news.services.AuthService;
import by.tkach.news.services.UserService;
import by.tkach.news.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    @Override
    @Transactional
    public void registerUser(User user) {
        if (userService.existsByUserName(user.getUserName())) {
            throw new CreateException(String.format("User with user name(login) '%s' already exist", user.getUserName()));
        }

        if (userService.existByFullName(user.getName(), user.getSurname(), user.getParentName())) {
            throw new CreateException("User with full name already exist");
        }

        userService.create(user);
    }

    @Override
    public AuthResponse loginUser(AuthRequest authRequest) {
        User user = userService.findByUserNameAndPassword(authRequest.getUserName(), authRequest.getPassword());
        String token = JwtUtils.generateToken(user.getUserName());
        return AuthResponse.builder().token(token).build();
    }
}
