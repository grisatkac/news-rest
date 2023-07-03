package by.tkach.news.dto.comments.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@Builder
public class CommentCreateRequest {

    @Size(max = 300, message = "Comment must contain max 300 characters")
    private String text;

    private Long idNews;
}
