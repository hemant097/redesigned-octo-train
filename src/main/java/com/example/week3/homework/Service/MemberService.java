package com.example.week3.homework.Service;


import com.example.week3.homework.Dto.MemberDto;
import com.example.week3.homework.Entity.Member;
import com.example.week3.homework.Exceptions.DuplicateResourceException;
import com.example.week3.homework.Exceptions.ResourceNotFoundException;
import com.example.week3.homework.Repos.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    public MemberDto addMember(MemberDto memberDto){

        String memberEmail = memberDto.getEmail();

        Optional<Member> memberOptional = memberRepository.findByEmail(memberEmail);

        if(memberOptional.isPresent())
            throw new DuplicateResourceException("this member is already added with email "+memberEmail);

        Member member = modelMapper.map(memberDto, Member.class);

        Member savedMember = memberRepository.save(member);

        return modelMapper.map(savedMember, MemberDto.class);
    }

    public MemberDto addMember(String memberEmail){

       Member member = checkIfMemberPresent(memberEmail);
       return modelMapper.map(member, MemberDto.class);
    }

    public Member checkIfMemberPresent(String email){
        return memberRepository.findByEmail(email)
                .orElseThrow( () -> new ResourceNotFoundException("member not found"));
    }


}
