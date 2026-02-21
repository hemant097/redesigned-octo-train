package com.example.week3.homework.Repos;

import com.example.week3.homework.Entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {

    @Query("SELECT br FROM BorrowRecord br WHERE br.member.email= :email")
    BorrowRecord findByMemberEmail(@Param("email") String email);
}
