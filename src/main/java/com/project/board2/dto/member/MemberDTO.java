package com.project.board2.dto.member;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Data;

//User 를 상속받으면 UserDetails를 들어갈수있다.
@Data
public class MemberDTO extends User implements OAuth2User{

    private String mname;
    // social login 시 email을 못잡음
    private String email;
    private String pw;

    public MemberDTO(String email,
      String mpw,
      String mname,
      List<String> roleNames){
        
        super(email,mpw,
         roleNames.stream().map(str -> new SimpleGrantedAuthority("ROLE_"+str))
         .collect(Collectors.toList())
        );
        this.mname = mname;
        this.email = email;
        this.pw = mpw;
    }

    @Override
    public Map<String, Object> getAttributes() {
      // TODO Auto-generated method stub
      //throw new UnsupportedOperationException("Unimplemented method 'getAttributes'");

      return null;
    }

    @Override
    public String getName() {

      return this.email;
    }

   
    
}
