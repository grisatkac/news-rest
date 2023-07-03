package by.tkach.news.mappers;

import by.tkach.news.dto.news.request.NewsCreateRequest;
import by.tkach.news.dto.news.request.NewsUpdateRequest;
import by.tkach.news.dto.news.response.NewsResponse;
import by.tkach.news.entities.News;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    News toNews(NewsCreateRequest news);

    News toNews(NewsUpdateRequest news);

    NewsResponse toNewsResponse(News news);
}
