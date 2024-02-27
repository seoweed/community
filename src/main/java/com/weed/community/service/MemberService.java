package com.weed.community.service;

import com.weed.community.domain.Member;
import com.weed.community.dto.MemberDto;
import com.weed.community.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원 저장
    public Member saveMember(MemberDto memberDto) {
        Member member = new Member(memberDto.getUsername(), memberDto.getEmail(), memberDto.getPassword(), LocalDateTime.now());
        return memberRepository.save(member);
    }
    // 로그인 서비스
    public Member loginService(String email, String password) {
        Member loginMember = memberRepository.findByEmail(email);
        if (loginMember == null) {
            return null;
        }
        return loginMember;
    }
}
