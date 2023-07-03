package by.tkach.news.dto.news.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@Builder
public class NewsUpdateRequest {

    private Long id;

    @NotEmpty
    @Size(max = 150, message = "Title of news must contain max 150 characters")
    private String title;

    @NotEmpty
    @Size(max = 2000, message = "Text of news must contain max 2000 characters")
    private String text;
}
