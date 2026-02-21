package com.example.week3.homework.Controller;

import com.example.week3.homework.Dto.AuthorDto;
import com.example.week3.homework.Service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    ResponseEntity<AuthorDto> getAuthor (@RequestParam String email){

        AuthorDto authorDto = authorService.getAuthorByEmail(email);
        return new ResponseEntity<>(authorDto, HttpStatus.OK);

    }

    @PostMapping
    ResponseEntity<AuthorDto> addNewAuthor(@Valid @RequestBody AuthorDto authorDto){

        AuthorDto savedAuthor = authorService.createNewAuthor(authorDto);
        return new ResponseEntity<>(savedAuthor, HttpStatus.OK);

    }
}
