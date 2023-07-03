package by.tkach.news.mappers;

import by.tkach.news.dto.comments.request.CommentCreateRequest;
import by.tkach.news.dto.comments.request.CommentUpdateRequest;
import by.tkach.news.dto.comments.response.CommentResponse;
import by.tkach.news.entities.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment toComment(CommentCreateRequest commentCreateRequest);

    Comment toComment(CommentUpdateRequest commentUpdateRequest);

    CommentResponse toCommentResponse(Comment comment);
}
