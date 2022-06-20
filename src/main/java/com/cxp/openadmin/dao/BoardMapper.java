package com.cxp.openadmin.dao;

import com.cxp.openadmin.domain.dto.front.BoardDto;
import com.cxp.openadmin.domain.entity.Board;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface BoardMapper extends Mapper<Board> {

    List<BoardDto> getBoardList(@Param("paramsMap") Map<String, Object> map);

    int insertItem(@Param("paramsMap") BoardDto dto);
    int updateItem(@Param("paramsMap") BoardDto dto);
    int deleteItem(@Param("paramsMap") BoardDto dto);

}