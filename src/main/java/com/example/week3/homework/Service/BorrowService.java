package com.example.week3.homework.Service;

import com.example.week3.homework.Dto.BorrowRecordDto;
import com.example.week3.homework.Entity.Book;
import com.example.week3.homework.Entity.BorrowRecord;
import com.example.week3.homework.Entity.Member;
import com.example.week3.homework.Entity.enums.BookState;
import com.example.week3.homework.Exceptions.ResourceNotFoundException;
import com.example.week3.homework.Repos.AuthorRepository;
import com.example.week3.homework.Repos.BookRepository;
import com.example.week3.homework.Repos.BorrowRecordRepository;
import com.example.week3.homework.Repos.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.example.week3.homework.Entity.enums.BookState.AVAILABLE;
import static com.example.week3.homework.Entity.enums.BookState.BORROWED;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final MemberService memberService;
    private final ModelMapper modelMapper;
    private final BookService bookService;
    private final BorrowRecordRepository borrowRecRepo;

    public BorrowRecordDto borrowABook(String memberEmail, String isbn){

        Member member = memberService.checkIfMemberPresent(memberEmail);
        Book book = bookService.getBookByISBN(isbn);

        if(book.getState()==BORROWED){
            BorrowRecord borrowRecord = borrowRecRepo.findByMemberEmail(memberEmail);

            LocalDate dueDate = borrowRecord.getDueDate();
            String comeAfterDate = dueDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

            throw new ResourceNotFoundException("Book not available , someone else has borrowed till :" + comeAfterDate);

        }


        book.setState(BORROWED);

        LocalDate localDate = LocalDate.now();

        BorrowRecord borrowRecord = BorrowRecord.builder()
                .book(book)
                .issueDate(localDate)
                .dueDate(localDate.plusDays(15))
                .member(member)
                .build();

        BorrowRecord savedRecord = borrowRecRepo.save(borrowRecord);

        BorrowRecordDto borrowRecordDto = new BorrowRecordDto();

        modelMapper.map(savedRecord, borrowRecordDto);

        return borrowRecordDto;



    }

    @Transactional
    public String returnABook(Long borrowRecordId){

        BorrowRecord borrowRecord = borrowRecRepo.findById(borrowRecordId)
                .orElseThrow( () -> new ResourceNotFoundException("no borrow related details found"));

        //assuming calling this method, means dueDate has arrived and the person has come to return this specific book

        Book borrowedBook = borrowRecord.getBook();
        borrowedBook.setState(AVAILABLE);
        borrowRecRepo.deleteById(borrowRecordId);

        return "borrowRecordDeleted Successfully";
    }


}
