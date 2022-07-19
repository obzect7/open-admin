package com.cxp.openadmin.service.front;

import com.cxp.openadmin.domain.dto.front.BoardDto;

import java.util.List;
import java.util.Map;

public interface ChartService {

    // 게시판 조회
    List<BoardDto> getChartList(Map<String, Object> map);
}
