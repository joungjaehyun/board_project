package com.project.board2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.project.board2.dto.PageRequestDTO;
import com.project.board2.dto.PageResponseDTO;
import com.project.board2.dto.board.BoardDTO;
import com.project.board2.dto.board.BoardInsertDTO;
import com.project.board2.service.BoardService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/board")
public class BoardController {
    
    private final BoardService service;

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('USER')")
    public void getList(PageRequestDTO pageRequestDTO, Model model){
        
         PageResponseDTO<BoardDTO> responseDTO 
        = service.getList(pageRequestDTO);


        log.info("get List");
        
        model.addAttribute("dto", responseDTO);
    }

    @GetMapping("/read/{bno}")
    @PreAuthorize("hasAnyRole('USER')")
    public String getRead(
        @PathVariable("bno") Long bno,
         PageRequestDTO pageRequestDTO,
         HttpServletRequest request,
        HttpServletResponse response, Model model){

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                log.info("cookie.getName " + cookie.getName());
                log.info("cookie.getValue " + cookie.getValue());

                if (!cookie.getValue().contains(request.getParameter("bno"))) {
                    cookie.setValue(cookie.getValue() + "_" + request.getParameter("bno"));
                    cookie.setMaxAge(60 * 60 * 2);  
                    response.addCookie(cookie);
                    service.addViewCnt(bno);
                 
                }
            }
        } else {
            Cookie newCookie = new Cookie("visit_cookie", request.getParameter("bno"));
            newCookie.setMaxAge(60 * 60 * 2);
            response.addCookie(newCookie);
            service.addViewCnt(bno);
        }

        

        log.info("get Read");
       
        model.addAttribute("dto", service.getOne(bno));

        return "/board/read";
    }

    @GetMapping("/regist")
    @PreAuthorize("hasAnyRole('USER')")
    public void getRegist(BoardDTO boardDTO){
        log.info("get regist");
    }

    @GetMapping("/modify/{bno}")
    @PreAuthorize("hasAnyRole('USER')")
    public String getModify(@PathVariable("bno") Long bno,  PageRequestDTO pageRequestDTO,Model model){

        log.info("get modify");
        model.addAttribute("dto", service.getOne(bno));

        return "/board/modify";
    }

    @PostMapping("/regist")
    @PreAuthorize("hasAnyRole('USER')")
    public String postRegist(BoardInsertDTO boardDTO){

        log.info("post regist...");

        service.insertOne(boardDTO);
        
        return "redirect:/board/list";
    }

    @PostMapping("/modify")
    @PreAuthorize("hasAnyRole('USER')")
    public String postModify(BoardInsertDTO boardDTO, RedirectAttributes rttr){

        log.info("post modify....");
        service.modifyOne(boardDTO);
        rttr.addFlashAttribute("message", boardDTO.getBno() + "번 수정완료.");
        return "redirect:/board/read/" + boardDTO.getBno();
    }

    @PostMapping("/delete/{bno}")
    @PreAuthorize("hasAnyRole('USER')")
    public String postDelete(@PathVariable("bno")Long bno, RedirectAttributes rttr){

        log.info("post delete..."); 
        service.deleteOne(bno);
        rttr.addFlashAttribute("message",bno + "번 삭제완료.");
        return "redirect:/board/list";
    }
    
}
