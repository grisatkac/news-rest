package by.tkach.news.dto.auth.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
public class AuthRequest {

    @Size(max = 40, message = "User name(login) must contain max 40 characters")
    private String userName;

    @NotEmpty
    private String password;
}
