package by.tkach.news.utils;

import by.tkach.news.config.security.CustomUserDetails;
import by.tkach.news.entities.User;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class AuthUserUtils {

    public static User getAuthUser() {
        CustomUserDetails auth = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return auth.getUser();
    }
}
