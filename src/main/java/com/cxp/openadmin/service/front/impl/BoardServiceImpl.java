package com.cxp.openadmin.service.front.impl;

import com.cxp.openadmin.dao.BoardMapper;
import com.cxp.openadmin.domain.dto.front.BoardDto;
import com.cxp.openadmin.service.front.BoardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {

    @Resource
    private BoardMapper boardMapper;

    // 게시판 조회
    @Override
    public List<BoardDto> getBoardList(Map<String, Object> map) {
        return boardMapper.getBoardList(map);
    }

    // 게시판 CRUD
    @Override
    public int saveBoard(BoardDto dto) {

        int save = 0;
        if(dto.getRow_status().equals("I")){
            // 01. post_no 채번
            dto.setPost_no(boardMapper.selectNextPostNo(dto));
            // 02. board insert
            save = boardMapper.insertBoard(dto);
        }else if(dto.getRow_status().equals("U")){
            save = boardMapper.updateBoard(dto);
        }else if(dto.getRow_status().equals("D")){
            save = boardMapper.deleteBoard(dto);
        }

        return save;
    }

    // 댓글 조회
    public List<BoardDto> getBoardComtList(Map<String, Object> map) {
        return boardMapper.getBoardComtList(map);
    }

    // 댓글 CRUD
    public int saveBoardComtList(BoardDto dto) {

        int save = 0;
        if(dto.getRow_status().equals("I")){
            // 01. post_comnt_no 채번
            dto.setPost_comnt_no(boardMapper.selectNextPostComntNo(dto));
            // 02. board insert
            save = boardMapper.insertBoardComnt(dto);
        }else if(dto.getRow_status().equals("D")){
            save = boardMapper.deleteBoardComnt(dto);
        }

        return save;
    }

    // 공지대상 게시판 조회
    public List<BoardDto> getNoticeBoardList(Map<String, Object> map) {
        return boardMapper.getNoticeBoardList(map);
    }

    // 게시판 조회 count
    public int saveBoardLookCount(BoardDto dto) {
        return boardMapper.saveBoardLookCount(dto);
    }

    // 게시판 첨부파일 번호 update
    public int saveBoardFileGrpSeq(BoardDto dto) {
        return boardMapper.saveBoardFileGrpSeq(dto);
    }
}
