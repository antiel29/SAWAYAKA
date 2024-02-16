package com.api.controllers;

import com.api.dtos.CommentDto;
import com.api.dtos.CommentNewDto;
import com.api.dtos.ThreadDto;
import com.api.dtos.ThreadNewDto;
import com.api.interfaces.ICommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@Tag(name = "Comments")
@SecurityRequirement(name = "bearer-jwt")
public class CommentController {
    private final ICommentService commentService;
    @Autowired
    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    @Operation(summary = "Get a list of all comments",description = "This endpoint returns a list of all comments.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of comments"),
    })
    public ResponseEntity<List<CommentDto>> getComments(){
        List<CommentDto> commentsDto = commentService.getComments();

        return ResponseEntity.ok(commentsDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a comment by id",description = "This endpoint returns a comment.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the comment"),
            @ApiResponse(responseCode = "404",description = "Comment not found")
    })
    public ResponseEntity<CommentDto> getComment(@PathVariable("id") Long id){
        CommentDto commentDto = commentService.getComment(id);

        if (commentDto == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(commentDto);
    }

    @GetMapping("/thread/{id}")
    @Operation(summary = "Get a list of all comments on a thread",description = "This endpoint returns a list of all comments.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of comments"),
    })
    public ResponseEntity<List<CommentDto>> getThreadComments(@PathVariable("id") Long id){
        List<CommentDto> commentsDto = commentService.getThreadComments(id);

        return ResponseEntity.ok(commentsDto);
    }
    @GetMapping("/current")
    @Operation(summary = "Get a list of all comments created by current logged user", description = "This endpoint returns a list of call comments.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of comments"),
    })
    public ResponseEntity<List<CommentDto>> getCurrentComments(){
        List<CommentDto> commentsDto = commentService.getCurrentComments();

        return ResponseEntity.ok(commentsDto);
    }


    @PostMapping("/new")
    @Operation(summary = "Create a new comment", description = "This endpoint allows you to comment in a thread.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
    })
    public ResponseEntity<?> newComment(@Valid @RequestBody CommentNewDto provided, BindingResult bindingResult){

        if (bindingResult.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());

        CommentDto commentDto =commentService.newComment(provided);

        return ResponseEntity.ok(commentDto);
    }

    @DeleteMapping("/{id}/delete")
    @Operation(summary = "Delete comment", description = "This endpoint allows you to delete one of your comments.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Comment not exist"),
    })
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id){
        CommentDto commentDto = commentService.getComment(id);

        if(commentDto == null)
            return ResponseEntity.notFound().build();

        if(commentService.deleteComment(id)){
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.badRequest().build();

        }
    }
}
