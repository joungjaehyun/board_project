package org.zerock.board2.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zerock.board2.dto.member.MemberConvertDTO;
import org.zerock.board2.dto.member.MemberReadDTO;



// MemberMapper Interface 
@Mapper
public interface MemberMapper {
    
    MemberReadDTO selectOne(String email);

  
    @Insert("INSERT INTO tbl_member_role (email, rolename) VALUES (#{email}, #{rolename})")
    int createJoinMemberRole(@Param("email") String email, @Param("rolename") String rolename);

    int signUpMember(MemberConvertDTO memberConvertDTO);


    int deleteMember(String email);


    int deleteMemberRole(String email);


    int updateMember(MemberConvertDTO memberConvertDTO);


    MemberConvertDTO selectMember(String email);
}