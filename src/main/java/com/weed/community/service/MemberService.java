package com.weed.community.service;

import com.weed.community.domain.Member;
import com.weed.community.dto.MemberDto;
import com.weed.community.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원 가입
    public Member join(MemberDto memberDto) {
        Member member = new Member(memberDto.getUsername(), memberDto.getEmail(), memberDto.getPassword(), LocalDateTime.now());
        return memberRepository.save(member);
    }
}
