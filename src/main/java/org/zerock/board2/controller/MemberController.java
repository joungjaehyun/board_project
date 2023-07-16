package org.zerock.board2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.board2.dto.member.MemberConvertDTO;
import org.zerock.board2.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
    
     // 의존성 주입
    private final MemberService memberService;

    // Autowired 명시적 표시
  

    // Data Not Found Exception
    public static class DataNotFoundException extends RuntimeException {
        public DataNotFoundException(String msg) {
            super(msg);
        }
    }

    @GetMapping("signin")
    @PreAuthorize("permitAll")
    public String signin() {

        return "/member/signin";
    }


    @GetMapping("signUp")
    @PreAuthorize("permitAll")
    public String getJoin() {
        return "/member/signUp";
    }

    @GetMapping("mypage/{email}")
    @PreAuthorize("hasAnyRole('USER')")
    public String myPage(@PathVariable("email") String email, Model model) {
       
        MemberConvertDTO member = memberService.readMember(email);
        model.addAttribute("member", member);
        return "/member/mypage";
    }

    @GetMapping("update/{email}")
    @PreAuthorize("hasAnyRole('USER')")
    public String getUpdateMember(@PathVariable("email") String email, Model model) {
     
        MemberConvertDTO member = memberService.readMember(email);
        model.addAttribute("member", member);
        return "/member/update";
    }

    @PostMapping("update")
    @PreAuthorize("hasAnyRole('USER')")
    public String postUpdateMember(MemberConvertDTO memberConvertDTO, RedirectAttributes redirectAttributes) {

        memberService.updateMember(memberConvertDTO);
        redirectAttributes.addFlashAttribute("message", "회원 업데이트 완료");
        return "redirect:/member/mypage/" + memberConvertDTO.getEmail();
    }

    @PostMapping("signUp")
    @PreAuthorize("permitAll")
    public String postJoinMember(MemberConvertDTO memberConvertDTO, RedirectAttributes redirectAttributes) {
 
        memberService.signUpMember(memberConvertDTO);
        redirectAttributes.addFlashAttribute("message", "회원 가입 완료");
        return "redirect:/member/signin";
    }

    // POST : Member Delete
    @PostMapping("delete/{email}")
    @PreAuthorize("hasAnyRole('USER')")
    public String postDelteMember(@PathVariable("email") String email, RedirectAttributes redirectAttributes) {
      
        memberService.deleteMember(email);
        redirectAttributes.addFlashAttribute("message", "회원 삭제 완료");
        return "redirect:/member/signin";
    }
}
