package com.example.week3.homework.Controller;

import com.example.week3.homework.Dto.BookDto;
import com.example.week3.homework.Service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping(path = "{id}")
    ResponseEntity<BookDto> getABook (@PathVariable(name = "id") Long bookId){

        BookDto fetchedBookDto = bookService.getBookDtoById(bookId);
        return new ResponseEntity<>(fetchedBookDto, HttpStatus.OK);

    }

    @PostMapping
    ResponseEntity<BookDto> addNewBook(@Valid @RequestBody BookDto bookDto){

        BookDto savedBook = bookService.addNewBookAndAssignAuthor(bookDto, bookDto.getAuthorEmail());

        return new ResponseEntity<>(savedBook, HttpStatus.OK);

    }
}
