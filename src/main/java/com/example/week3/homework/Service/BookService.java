package com.example.week3.homework.Service;


import com.example.week3.homework.Dto.BookDto;
import com.example.week3.homework.Entity.Author;
import com.example.week3.homework.Entity.Book;
import com.example.week3.homework.Exceptions.DuplicateResourceException;
import com.example.week3.homework.Exceptions.ResourceNotFoundException;
import com.example.week3.homework.Repos.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepo;
    private final AuthorService authorService;
    private final ModelMapper modelMapper;

    public BookDto addNewBookAndAssignAuthor(BookDto bookDto, String authorEmail){

        Optional<Book> bookOptional = bookRepo.findByIsbn(bookDto.getIsbn());

        if(bookOptional.isPresent())
            throw new DuplicateResourceException("this book is already present with isbn "+bookDto.getIsbn());

        Book book = modelMapper.map(bookDto, Book.class);

        Author author = authorService.whetherAuthorPresent(authorEmail);
        book.setAuthor(author);

        Book savedBook = bookRepo.save(book);

        return modelMapper.map(savedBook, BookDto.class);

    }

    public BookDto getBookDtoById(Long bookId) {

        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("no book found"));

        return modelMapper.map(book, BookDto.class);

    }

    public Book getBookByISBN(String ISBN) {

        return bookRepo.findByIsbn(ISBN)
                .orElseThrow(() -> new ResourceNotFoundException("no book found"));

    }


}
