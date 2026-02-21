package com.example.week3.homework.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {


    @Id
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "category")
    private Set<Book> books = new HashSet<>();
}
