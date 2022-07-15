package com.cxp.openadmin.service.backed.impl;

import com.cxp.openadmin.dao.CmCodeGrpMapper;
import com.cxp.openadmin.dao.MenuMgtMapper;
import com.cxp.openadmin.domain.dto.backed.CmCodeDto;
import com.cxp.openadmin.domain.dto.backed.SaveCmCodeGrpDto;
import com.cxp.openadmin.domain.dto.backed.SaveMenuMgtDto;
import com.cxp.openadmin.domain.entity.SysMenu;
import com.cxp.openadmin.domain.vo.backed.SysMenuVo;
import com.cxp.openadmin.service.backed.MenuMgtService;
import com.cxp.openadmin.util.TreeParser;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MenuMgtServiceImpl implements MenuMgtService {

    @Resource
    private CmCodeGrpMapper cmCodeGrpMapper;

    @Resource
    private MenuMgtMapper mapper;

    @Override
    public List<SaveMenuMgtDto> getMenuList(Map<String, Object> map) {
        return mapper.getMenuList(map);
    }

    @Override
    public int saveSaveMenuList(List<SaveMenuMgtDto> list) {
        int updateRow = 0;
        if(list.size()> 0){
            for(SaveMenuMgtDto dto : list){
                if("I".equals(dto.getRow_status())){
                    updateRow += mapper.insertMenuList(dto);
                    //insert
                }else if("U".equals(dto.getRow_status())){
                    updateRow += mapper.updateMenuList(dto);
                    //update
                }else if("D".equals(dto.getRow_status())){
                    updateRow += mapper.deleteMenuList(dto);
                    //delete
                }
            }
        }
        return updateRow;
    }
}
