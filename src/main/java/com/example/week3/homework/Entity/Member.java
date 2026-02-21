package com.example.week3.homework.Entity;

import com.example.week3.homework.Entity.enums.MemberStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    private Long memberId;
    private String name;
    private String email;
    private MemberStatus status;
}
