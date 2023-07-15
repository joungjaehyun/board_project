package org.zerock.board2.service;


import org.springframework.transaction.annotation.Transactional;
import org.zerock.board2.dto.PageRequestDTO;
import org.zerock.board2.dto.PageResponseDTO;
import org.zerock.board2.dto.board.BoardDTO;
import org.zerock.board2.dto.board.BoardInsertDTO;
import org.zerock.board2.dto.board.BoardReadDTO;

@Transactional
public interface BoardService {
    
    PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO);
    BoardReadDTO getOne(Long bno);
    void insertOne(BoardInsertDTO boardDTO);
    void deleteOne(Long bno);
    void modifyOne(BoardInsertDTO boardDTO);
    void addViewCnt(Long bno);
}
