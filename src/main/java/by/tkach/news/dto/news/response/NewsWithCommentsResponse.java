package by.tkach.news.dto.news.response;


import by.tkach.news.entities.Comment;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@Builder
public class NewsWithCommentsResponse {
    NewsResponse news;
    Page<Comment> comments;
}
