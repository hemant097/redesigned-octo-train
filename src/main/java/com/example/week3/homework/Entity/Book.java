package com.example.week3.homework.Entity;

import com.example.week3.homework.Entity.enums.BookState;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data@AllArgsConstructor@NoArgsConstructor
public class Book {

    @Id
    private Long id;
    private String title;
    private String isbn;
    private Integer pageCount;
    private BookState state;
    private LocalDate addedDate;


    @ManyToOne
    @JoinColumn(name = "author_id" , nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "cat_id", nullable = false)
    private Category category;

    //owning side



}
