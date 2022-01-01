package com.example.springtest.controller;

import com.example.springtest.domain.Member;
import com.example.springtest.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;
    //@Autowired private Memberservice memberService; Field Injection. 별로 안좋음.
    //@Autowired public void setMemberService(MemberService memberService){this.~}
    // Setter Injection. Public 이라서 쓰면 보안에 위험.
    @Autowired // DI(Dependancy Injection -> 생성자 주입)
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }
    
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
