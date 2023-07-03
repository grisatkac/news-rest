package by.tkach.news.dto.news.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class NewsResponse {
    private Long id;
    private String title;
    private String text;
    private LocalDate creationDate;
    private LocalDate lastEditDate;
    private Long insertedById;
    private Long updatedById;
}
