package com.api.controllers;

import com.api.dtos.ThreadDto;
import com.api.dtos.ThreadNewDto;
import com.api.interfaces.IThreadService;

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
@RequestMapping("/api/threads")
@Tag(name = "Threads")
@SecurityRequirement(name = "bearer-jwt")
public class ThreadController {

    private final IThreadService threadService;


    @Autowired
    public ThreadController(IThreadService threadService) {
        this.threadService = threadService;

    }

    @GetMapping
    @Operation(summary = "Get a list of all threads", description = "This endpoint returns a list of all threads.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of threads"),
    })
    public ResponseEntity<List<ThreadDto>> getThreads(){
        List<ThreadDto> threadsDto = threadService.getThreads();

        return ResponseEntity.ok(threadsDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a thread by id", description = "This endpoint returns a thread.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the thread"),
            @ApiResponse(responseCode = "404",description = "Thread not found"),
    })
    public ResponseEntity<ThreadDto> getThread(@PathVariable("id") Long id){
        ThreadDto threadDto = threadService.getThreadDto(id);

        if(threadDto == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(threadDto);
    }

    @GetMapping("/current")
    @Operation(summary = "Get a list of all threads created by current logged user", description = "This endpoint returns a list of created threads.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of threads"),
    })
    public ResponseEntity<List<ThreadDto>> getCurrentThreads(){
        List<ThreadDto> threadsDto = threadService.getCurrentThreads();

        return ResponseEntity.ok(threadsDto);
    }

    @PostMapping("/new")
    @Operation(summary = "Create a new thread", description = "This endpoint allows you to create a new thread in the forum.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
    })
    public ResponseEntity<?> newThread(@Valid @RequestBody ThreadNewDto provided, BindingResult bindingResult){

        if (bindingResult.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());

        threadService.newThread(provided);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/delete")
    @Operation(summary = "Delete thread", description = "This endpoint allows you to delete one of your threads.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Thread not exist"),
    })
    public ResponseEntity<?> deleteThread(@PathVariable("id") Long id){
        ThreadDto threadDto = threadService.getThreadDto(id);

        if(threadDto == null)
            return ResponseEntity.notFound().build();

        if(threadService.deleteThread(id)){
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.badRequest().build();

        }
    }



}
