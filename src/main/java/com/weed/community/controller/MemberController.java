package com.weed.community.controller;

import com.weed.community.domain.Member;
import com.weed.community.dto.LoginRequest;
import com.weed.community.dto.MemberDto;
import com.weed.community.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    // 회원가입
    @GetMapping("/join")
    public String join() {
        return "join";
    }
    @PostMapping("/join")
    public String join(@ModelAttribute MemberDto memberDto) {
        memberService.saveMember(memberDto);
        return "redirect:/";
    }
    // 로그인
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest loginRequest, HttpServletRequest request) {
        Member loginMember = memberService.loginService(loginRequest.getEmail(), loginRequest.getPassword());
        // 로그인 실패시
        if (loginMember == null) {
            return "login";
        }
        // 로그인 성공시
        HttpSession session = request.getSession();
        session.setAttribute("loginMember", loginMember);
        return "redirect:/";
    }
    // 로그아웃
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
