package by.tkach.news.services;



import by.tkach.news.dto.auth.request.AuthRequest;
import by.tkach.news.dto.auth.response.AuthResponse;
import by.tkach.news.entities.User;

public interface AuthService {

    void registerUser(User user);

    AuthResponse loginUser(AuthRequest authRequest);
}
