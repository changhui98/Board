package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/members/new")
    public String newMemberForm(){
        return "members/new";
    }

    @GetMapping("/signup")
    public String signUpPage(){
        return "members/new";
    }

    @PostMapping("/join")
    public String join(MemberForm form){
        Member member = form.toEntity();
        log.info(member.toString());

        Member saved = memberRepository.save(member);
        log.info(saved.toString());
        return "redirect:/members";
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model){
        Member member = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", member);

        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model){
        Iterable<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);

        return "members/index";
    }

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        // 수정할 데이터 가져오기
        Member member = memberRepository.findById(id).orElse(null);

        // 모델에 데이터 등록하기
        model.addAttribute("member", member);

        // 뷰 페이지 설정하기
        return "members/edit";
    }

    @PostMapping("/members/update")
    public String update(MemberForm form){
        // 1. DTO를 엔티티로 변환하기
        Member member = form.toEntity();
        log.info(member.toString());

        // 2. 엔티티를 DB에 저장하기
        // 2-1. DB에서 기존 데이터 가져오기
        Member target = memberRepository.findById(member.getId()).orElse(null);

        // 2-2. 기존 데이터 값을 갱신하기
        if(target != null){
            memberRepository.save(member);
        }

        // 3. 수정 결과 페이지로 리다이렉트하기
        return "redirect:/members/" + member.getId();
    }

    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){

        Member member = memberRepository.findById(id).orElse(null);

        if(member != null){
            memberRepository.delete(member);
            rttr.addFlashAttribute("msg","해당 회원을 탈퇴시켰습니다.!");
        }
        return "redirect:/members";
    }

}
