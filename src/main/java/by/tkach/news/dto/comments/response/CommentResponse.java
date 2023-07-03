package by.tkach.news.dto.comments.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CommentResponse {
    private Long id;
    private String text;
    private LocalDate creationDate;
    private LocalDate lastEditDate;
    private Long insertedById;
    private Long idNews;
}
