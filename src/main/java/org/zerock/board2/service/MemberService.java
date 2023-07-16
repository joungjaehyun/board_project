package org.zerock.board2.service;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.board2.dto.member.MemberConvertDTO;


@Transactional
public interface MemberService {


    int signUpMember(MemberConvertDTO memberConvertDTO);


    int updateMember(MemberConvertDTO memberConvertDTO);


    int deleteMember(String email);

 
    MemberConvertDTO readMember(String email);
}