package com.example.week3.homework.Dto;

import com.example.week3.homework.Entity.Book;
import com.example.week3.homework.Entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BorrowRecordDto {

    private Long id;

    private LocalDate issueDate;

    private LocalDate dueDate;

    private LocalDate returnedDate;

    private String memberEmail;

    private String bookIsbn;
}
