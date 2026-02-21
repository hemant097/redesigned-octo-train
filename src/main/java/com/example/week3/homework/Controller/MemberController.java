package com.example.week3.homework.Controller;

import com.example.week3.homework.Dto.MemberDto;
import com.example.week3.homework.Service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    ResponseEntity<MemberDto> createMember(@Valid @RequestBody MemberDto memberDto){

        MemberDto savedMember = memberService.addMember(memberDto);

        return ResponseEntity.ok(savedMember);

    }
}
