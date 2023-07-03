package by.tkach.news.dto.auth.request;

import by.tkach.news.entities.enums.Role;
import by.tkach.news.userRole.ValueOfRoleEnum;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
public class UserRegistrationRequest {

    @NotEmpty(message = "User name can't be empty")
    @Size(max = 40, message = "User name(login name) must contain max 40 characters")
    private String userName;

    @NotEmpty(message = "Name can't be empty")
    @Size(max = 20, message = "User name contain max 20 characters")
    private String name;

    @NotEmpty(message = "Surname can't empty")
    @Size(max = 20, message = "Surname must contain max 20 characters")
    private String surname;

    @NotEmpty(message = "Parent name can't be empty")
    @Size(max = 20, message = "Parent name must contain max 20 characters")
    private String parentName;

    @NotEmpty(message = "Parent name can't be empty")
    @ValueOfRoleEnum(enumClass = Role.class, message = "Role not exist")
    private String role;
}
