package com.cxp.openadmin.service.front;

import com.cxp.openadmin.domain.dto.front.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardService {

    // 게시판 조회
    List<BoardDto> getBoardList(Map<String, Object> map);

    // 게시판 CRUD
    int saveBoard(BoardDto dto);

    // 댓글 조회
    List<BoardDto> getBoardComtList(Map<String, Object> map);

    // 댓글 CRUD
    int saveBoardComtList(BoardDto dto);

    // 공지대상 게시판 조회
    List<BoardDto> getNoticeBoardList(Map<String, Object> map);

    // 게시판 조회 count
    int saveBoardLookCount(BoardDto dto);

    // 게시판 첨부파일 번호 update
    int saveBoardFileGrpSeq(BoardDto dto);
}
