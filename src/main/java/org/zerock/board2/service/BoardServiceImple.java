package org.zerock.board2.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.zerock.board2.dto.PageRequestDTO;
import org.zerock.board2.dto.PageResponseDTO;
import org.zerock.board2.dto.board.BoardDTO;
import org.zerock.board2.dto.board.BoardInsertDTO;
import org.zerock.board2.dto.board.BoardReadDTO;
import org.zerock.board2.mappers.BoardMapper;
import org.zerock.board2.mappers.FileMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImple implements BoardService {

    private final BoardMapper boardMapper;
    private final FileMapper fileMapper;

    @Override
    public PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO) {

        List<BoardDTO> list = boardMapper.getList(pageRequestDTO);
        long total = boardMapper.listCount(pageRequestDTO);

        return PageResponseDTO.<BoardDTO>withAll()
                .list(list)
                .totalPage(total)
                .build();
    }

    @Override
    public BoardReadDTO getOne(Long bno) {

        return boardMapper.getOne(bno);
    }

    @Override
    public void insertOne(BoardInsertDTO boardDTO) {

        AtomicInteger index = new AtomicInteger(0);
        List<String> fileNames = boardDTO.getFileName();
        Long bno = boardDTO.getBno();
        if (boardDTO.getFileName() != null && !boardDTO.getFileName().isEmpty()) {

            List<Map<String, String>> list = fileNames.stream().map(str -> {

                String uuid = str.substring(0, 36);

                String fileName = str.substring(37);

             return Map.of("uuid", uuid, "fileName", fileName, "tno", "" + bno, "ord", "" + index.getAndIncrement());

            }).collect(Collectors.toList());

            fileMapper.createImage(list);
        }

        boardMapper.insertOne(boardDTO);
    }

    @Override
    public void deleteOne(Long bno) {
        boardMapper.deleteOne(bno);
    }

    @Override
    public void modifyOne(BoardInsertDTO boardDTO) {
        
        fileMapper.deleteImage(boardDTO.getBno());
        AtomicInteger index = new AtomicInteger(0);
        List<String> fileNames = boardDTO.getFileName();
        Long bno = boardDTO.getBno();
        if (boardDTO.getFileName() != null && !boardDTO.getFileName().isEmpty()) {

            List<Map<String, String>> list = fileNames.stream().map(str -> {

                String uuid = str.substring(0, 36);

                String fileName = str.substring(37);

             return Map.of("uuid", uuid, "fileName", fileName, "tno", "" + bno, "ord", "" + index.getAndIncrement());

            }).collect(Collectors.toList());

            fileMapper.createImage(list);
        }


        boardMapper.modifyOne(boardDTO);
    }

    @Override
    public void addViewCnt(Long bno) {
        boardMapper.incremetViewCnt(bno);
    }

}
