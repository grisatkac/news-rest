package by.tkach.news.dto.comments.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Builder
public class CommentUpdateRequest {

    @NotNull
    private Long id;

    @Size(min = 5, max = 300, message = "Comment must have max 300 characters")
    private String text;
}
