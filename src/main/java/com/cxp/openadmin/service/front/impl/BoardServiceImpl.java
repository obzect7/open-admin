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

    @Override
    public List<BoardDto> getBoardList(Map<String, Object> map) {
        return boardMapper.getBoardList(map);
    }

    @Override
    public int saveItem(List<BoardDto> list) {

        int updateRow = 0;
        if(list.size()> 0){
            for(BoardDto dto : list){
                if("I".equals(dto.getRow_status())){
                    updateRow += boardMapper.insertItem(dto);
                }else if("U".equals(dto.getRow_status())){
                    updateRow += boardMapper.updateItem(dto);
                }else if("D".equals(dto.getRow_status())){
                    updateRow += boardMapper.deleteItem(dto);
                }
            }
        }
        return updateRow;
    }



}
