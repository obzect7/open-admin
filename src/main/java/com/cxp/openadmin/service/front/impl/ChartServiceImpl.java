package com.cxp.openadmin.service.front.impl;

import com.cxp.openadmin.dao.ChartMapper;
import com.cxp.openadmin.domain.dto.front.BoardDto;
import com.cxp.openadmin.service.front.ChartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ChartServiceImpl implements ChartService {

    @Resource
    private ChartMapper chartMapper;

    // 게시판 조회
    @Override
    public List<BoardDto> getChartList(Map<String, Object> map) {
        return chartMapper.getChartList(map);
    }

}
