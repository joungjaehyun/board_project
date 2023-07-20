package com.project.board2.dto.board;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardInsertDTO {
    
    private Long bno;
    private String title;
    private String content;
    private String writer;
    @Builder.Default
    private List<String> fileName = new ArrayList<>();;
}
