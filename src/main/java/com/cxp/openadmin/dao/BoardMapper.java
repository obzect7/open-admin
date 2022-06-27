package com.cxp.openadmin.dao;

import com.cxp.openadmin.domain.dto.front.BoardDto;
import com.cxp.openadmin.domain.entity.Board;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface BoardMapper extends Mapper<Board> {

    List<BoardDto> getBoardList(@Param("paramsMap") Map<String, Object> map);

    // 게시물 생성
    int insertBoard(@Param("paramsMap") BoardDto dto);

    // 게시물 수정
    int updateBoard(@Param("paramsMap") BoardDto dto);

    // 게시물 삭제
    int deleteBoard(@Param("paramsMap") BoardDto dto);

    // 게시물 번호 채번(max+1)
    String selectNextPostNo(@Param("paramsMap") BoardDto dto);

}