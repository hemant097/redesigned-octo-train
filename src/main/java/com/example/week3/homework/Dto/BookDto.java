package com.example.week3.homework.Dto;

import com.example.week3.homework.Entity.enums.BookCategory;
import com.example.week3.homework.Entity.enums.BookState;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String isbn;

    @NotNull
    private BookCategory category;

    @NotNull
    private BookState state;

    @Positive
    private Integer pageCount;

    private LocalDate addedDate;

    @Email
    private String authorEmail;
}
