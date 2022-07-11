package com.cxp.openadmin.service.front.impl;

import com.cxp.openadmin.dao.ItemMapper;
import com.cxp.openadmin.domain.dto.front.ItemDto;
import com.cxp.openadmin.service.front.ItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private ItemMapper itemMapper;

    @Override
    public List<ItemDto> getItemList(Map<String, Object> map) {
        return itemMapper.getItemList(map);
    }

    @Override
    public int saveItem(List<ItemDto> list) {

        int updateRow = 0;
        if(list.size()> 0){
            for(ItemDto dto : list){
                if("I".equals(dto.getRow_status())){
                    updateRow += itemMapper.insertItem(dto);
                }else if("U".equals(dto.getRow_status())){
                    updateRow += itemMapper.updateItem(dto);
                }else if("D".equals(dto.getRow_status())){
                    updateRow += itemMapper.deleteItem(dto);
                }
            }
        }
        return updateRow;
    }

    @Override
    public int saveFileGrpSeq(ItemDto item) {
        int updateRow = itemMapper.updateFileGrpSeq(item);
        return updateRow;
    }
}
