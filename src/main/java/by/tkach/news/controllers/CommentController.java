package by.tkach.news.controllers;

import by.tkach.news.dto.comments.request.CommentCreateRequest;
import by.tkach.news.dto.comments.request.CommentUpdateRequest;
import by.tkach.news.dto.comments.response.CommentResponse;
import by.tkach.news.entities.Comment;
import by.tkach.news.mappers.CommentMapper;
import by.tkach.news.services.CommentService;
import by.tkach.news.services.facade.CommentFacade;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Class comment controller handles all request whose path starts with 'comments'.
 * @author Grisha Tkach
 * @version 1.0.0
 */
@RestController
@AllArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    /** Field which contains logic for interacting with comments. */
    private final CommentService commentService;

    /** Field which contains logic for interacting with comments, which consists of many simple operations. */
    private final CommentFacade commentFacade;

    /** Field contains methods for type conversion */
    private final CommentMapper commentMapper;

    /**
     * Create new comment {@link Comment}. Comment can create user with ADMIN, SUBSCRIBER roles.
     * @param commentCreateRequest {@link CommentCreateRequest} - idNews in this request must exist.
     * @return {@link CommentResponse}
     */
    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_SUBSCRIBER') or hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponse create(@Valid @RequestBody CommentCreateRequest commentCreateRequest) {
        Comment createdComment = commentFacade.createComment(commentMapper.toComment(commentCreateRequest));
        return commentMapper.toCommentResponse(createdComment);
    }

    /**
     * Returns a comment with the given identifier. If a comment with the provided identifier does not exist, an error will be thrown {@link by.tkach.news.exception.NotFoundException} exception. This operation can be performed with any role and unauthorized.
     * @param id - must belong to an existing comment.
     * @return {@link CommentResponse}
     */
    @GetMapping("/{id}")
    public CommentResponse getById(@PathVariable Long id) {
        Comment foundComment = commentService.findById(id);
        return commentMapper.toCommentResponse(foundComment);
    }

    /**
     * Returns a sublist of a list of comments for the given {@link Pageable}. This operation can be performed with any role and unauthorized
     * @param pageable - must be not null.
     * @return {@link Page} of {@link Comment} - sublist of a list of comments
     */
    @GetMapping("/all")
    public Page<CommentResponse> getAll(@PageableDefault(size = 5, sort = "creationDate", direction = Sort.Direction.DESC)Pageable pageable) {
        return commentService.findAllPaginated(pageable).map(commentMapper::toCommentResponse);
    }

    /**
     * Receive {@link CommentUpdateRequest} to update comment. Comment can update user with ADMIN, SUBSCRIBER roles Return updated comment.
     * @param commentUpdateRequest {@link CommentResponse} - object to update exist comment, must be not null.
     * @return {@link CommentResponse} - updated comment.
     */
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_SUBSCRIBER') or hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CommentResponse update(@Valid @RequestBody CommentUpdateRequest commentUpdateRequest) {
        Comment updatedComment = commentFacade.updateComment(commentMapper.toComment(commentUpdateRequest));
        return commentMapper.toCommentResponse(updatedComment);
    }

    /**
     * Delete comment with the given identifier. If a comment with the provided identifier does not exist, an error will be thrown {@link by.tkach.news.exception.NotFoundException} exception.
     * @param id - comment id to delete. Comment can delete user with ADMIN, SUBSCRIBER roles. Return deleted comment.
     * @return {@link CommentResponse} - deleted comment.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_SUBSCRIBER') or hasRole('ROLE_ADMIN')")
    public CommentResponse deleteById(@PathVariable Long id) {
        Comment deletedComment = commentFacade.deleteComment(id);
        return commentMapper.toCommentResponse(deletedComment);
    }
}
