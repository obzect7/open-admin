package com.cxp.openadmin.service.backed.impl;

import com.cxp.openadmin.dao.TMstPlantMapper;
import com.cxp.openadmin.domain.dto.backed.TmstPlantDto;
import com.cxp.openadmin.service.backed.MstPlantService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class MstPlantServiceImpl implements MstPlantService {

    @Resource
    private TMstPlantMapper mstPlantMapper;

    @Override
    public List<TmstPlantDto> getMstPlantList(Map<String, Object> map) {
        return mstPlantMapper.getMstPlantList(map);
    }
    @Override
    public int saveMstPlant(List<TmstPlantDto> list) {
        int updateRow = 0;
        if(list.size()> 0){
            for(TmstPlantDto dto : list){
                if("I".equals(dto.getRow_status())){
                    updateRow += mstPlantMapper.insertMstPlant(dto);
                    //insert
                }else if("U".equals(dto.getRow_status())){
                    updateRow += mstPlantMapper.updateMstPlant(dto);
                    //update
                }else if("D".equals(dto.getRow_status())){
                    updateRow += mstPlantMapper.deleteMstPlant(dto);
                    //delete
                }
            }
        }
        return updateRow;
    }

}
