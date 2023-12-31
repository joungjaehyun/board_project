package com.project.board2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class UploadFileDTO {
    private String uuid;
    private String fileName;
    private boolean img;

    public String getLink() {
        if (img) {
            return "s_" + uuid + "_" + fileName;
        } else {
            return "default.jpg";
        }
    }
}
