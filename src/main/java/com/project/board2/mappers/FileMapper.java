package com.project.board2.mappers;


import java.util.List;
import java.util.Map;
// file upload 관련 
public interface FileMapper {


    int createImage(List<Map<String, String>> imageList);


    int deleteImage(Long tno);


    int updateImage(List<Map<String, String>> imageList);


}