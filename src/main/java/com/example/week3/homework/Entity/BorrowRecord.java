package com.example.week3.homework.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowRecord {

    @Id
    private Long id;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnedDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Member member;
}
