package com.example.week3.homework.Service;

import com.example.week3.homework.Dto.AuthorDto;
import com.example.week3.homework.Entity.Author;
import com.example.week3.homework.Exceptions.DuplicateResourceException;
import com.example.week3.homework.Exceptions.ResourceNotFoundException;
import com.example.week3.homework.Repos.AuthorRepository;
import com.example.week3.homework.Repos.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepo;
    private final BookRepository bookRepo;
    private final ModelMapper modelMapper;


    public AuthorDto createNewAuthor(AuthorDto authorDto){

        String authorEmail = authorDto.getEmail();

        Optional<Author> authorOptional = authorRepo.findByEmail(authorEmail);

        if(authorOptional.isPresent())
            throw new DuplicateResourceException("this author is already added with email "+authorEmail);

       Author author = modelMapper.map(authorDto, Author.class);

       Author savedAuthor = authorRepo.save(author);

       return modelMapper.map(savedAuthor, AuthorDto.class);

    }

    public AuthorDto getAuthorByEmail(String authorEmail) {

        Author author =  authorRepo.findByEmail(authorEmail)
                .orElseThrow( () ->  new ResourceNotFoundException(" no author found with these details"));

        return modelMapper.map(author, AuthorDto.class);

    }

    Author whetherAuthorPresent(String email){
        return authorRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("no author is present with this email "+email));
    }
}
