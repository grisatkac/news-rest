package by.tkach.news.mappers;

import by.tkach.news.dto.auth.request.UserRegistrationRequest;
import by.tkach.news.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserRegistrationRequest user);
}
