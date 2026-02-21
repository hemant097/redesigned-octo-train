package com.example.week3.homework.Entity;

import com.example.week3.homework.Entity.enums.BookCategory;
import com.example.week3.homework.Entity.enums.BookState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data@AllArgsConstructor@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(unique = true)
    private String isbn;

    private Integer pageCount;

    @Enumerated(EnumType.STRING)
    private BookState state;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookCategory category;

    @CreationTimestamp
    private LocalDate addedDate;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Set<BorrowRecord> borrowRecords = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "author_id" , nullable = false)
    private Author author;

    //owning side



}
