package com.example.week3.homework.Controller;

import com.example.week3.homework.Dto.BorrowRecordDto;
import com.example.week3.homework.Service.BorrowService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/borrow")
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowService borrowService;

    @PostMapping
    ResponseEntity<BorrowRecordDto> borrowABook(@Valid @RequestBody BorrowRecordDto borrowRecordDto){

        String memberEmail = borrowRecordDto.getMemberEmail();
        String ISBN = borrowRecordDto.getBookIsbn();

        BorrowRecordDto savedBorrowRecord = borrowService.borrowABook(memberEmail , ISBN);

        return ResponseEntity.ok(savedBorrowRecord);

    }

    @DeleteMapping(path = "/remove/{id}")
    ResponseEntity<String> deleteABorrowRecord(@PathVariable(name = "id") Long borrowRecordId){

        String message = borrowService.returnABook(borrowRecordId);

        return ResponseEntity.ok(message);

    }
}
