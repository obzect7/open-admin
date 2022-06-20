package com.cxp.openadmin.service.front;

import com.cxp.openadmin.domain.dto.front.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardService {

    List<BoardDto> getBoardList(Map<String, Object> map);

    int saveItem(List<BoardDto> list);
}
