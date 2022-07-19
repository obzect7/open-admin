package com.cxp.openadmin.dao;

import com.cxp.openadmin.domain.dto.front.BoardDto;
import com.cxp.openadmin.domain.entity.Board;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface ChartMapper extends Mapper<Board> {

    // 게시물 리스트 조회
    List<BoardDto> getChartList(@Param("paramsMap") Map<String, Object> map);

}