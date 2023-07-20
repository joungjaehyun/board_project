package com.project.board2.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.board2.dto.member.MemberConvertDTO;
import com.project.board2.mappers.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImple implements MemberService {
  
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;


    // Join Member 
    @Override
    @Transactional
    public int signUpMember(MemberConvertDTO memberConvertDTO) {
        
        String passwordEncode = passwordEncoder.encode(memberConvertDTO.getMpw());
        memberConvertDTO.setMpw(passwordEncode);
        String roleName = "USER";
        memberMapper.createJoinMemberRole(memberConvertDTO.getEmail(), roleName);
        return memberMapper.signUpMember(memberConvertDTO);
    }

    // Update Member 
    @Override
    @Transactional
    public int updateMember(MemberConvertDTO memberConvertDTO) {
      
        String passwordEncode = passwordEncoder.encode(memberConvertDTO.getMpw());
        memberConvertDTO.setMpw(passwordEncode);
        return memberMapper.updateMember(memberConvertDTO);
    }

    // Delete Member 
    @Override
    @Transactional
    public int deleteMember(String email) {

        memberMapper.deleteMemberRole(email);
        return memberMapper.deleteMember(email);
    }

    // Read Member 
    @Override
    @Transactional(readOnly = true)
    public MemberConvertDTO readMember(String email) {
       
        return memberMapper.selectMember(email);
    }
}
