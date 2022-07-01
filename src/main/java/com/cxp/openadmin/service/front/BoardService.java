package com.cxp.openadmin.service.front;

import com.cxp.openadmin.domain.dto.front.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardService {

    List<BoardDto> getBoardList(Map<String, Object> map);

    int saveBoard(BoardDto dto);

    List<BoardDto> getBoardComtList(Map<String, Object> map);

    int saveBoardComtList(BoardDto dto);
}
