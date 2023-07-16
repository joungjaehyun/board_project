package org.zerock.board2.dto.member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberConvertDTO {
    // tbl_member
    private String email;
    private String mpw;
    private String mname;

}