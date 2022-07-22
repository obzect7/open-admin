package com.cxp.openadmin.dao;

import com.cxp.openadmin.domain.dto.front.BoardDto;
import com.cxp.openadmin.domain.entity.Board;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface BoardMapper extends Mapper<Board> {

    // 게시물 리스트 조회
    List<BoardDto> getBoardList(@Param("paramsMap") Map<String, Object> map);

    // 게시물 생성
    int insertBoard(@Param("paramsMap") BoardDto dto);

    // 게시물 수정
    int updateBoard(@Param("paramsMap") BoardDto dto);

    // 게시물 삭제
    int deleteBoard(@Param("paramsMap") BoardDto dto);

    // 게시물 번호 채번(max+1)
    String selectNextPostNo(@Param("paramsMap") BoardDto dto);

    // 댓글 조회
    List<BoardDto> getBoardComtList(@Param("paramsMap") Map<String, Object> map);

    // 게시물 번호 채번(max+1)
    String selectNextPostComntNo(@Param("paramsMap") BoardDto dto);

    // 댓글 생성
    int insertBoardComnt(@Param("paramsMap") BoardDto dto);

    // 댓글 삭제
    int deleteBoardComnt(@Param("paramsMap") BoardDto dto);

    // 공지대상 게시판 조회
    List<BoardDto> getNoticeBoardList(@Param("paramsMap") Map<String, Object> map);

    // 게시판 조회 count
    int saveBoardLookCount(@Param("paramsMap") BoardDto dto);

}