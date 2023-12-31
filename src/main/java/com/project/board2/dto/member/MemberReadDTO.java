package com.project.board2.dto.member;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberReadDTO {
    
    private String email;
    private String mpw;
    private String mname;
    private List<String> rolenames;

}
