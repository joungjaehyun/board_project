package com.project.board2.dto.board;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardReadDTO {
    
    private Long bno;
    private String title;
    private String content;
    private String writer;
    private String regdate;
    private String moddate;
    private Integer viewCnt;
    private Integer rCnt;
    private List<String> fileName;
}