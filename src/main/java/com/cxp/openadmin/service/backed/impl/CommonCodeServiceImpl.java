package com.cxp.openadmin.service.backed.impl;

import com.cxp.openadmin.dao.CmCodeGrpMapper;
import com.cxp.openadmin.dao.CmCodeMapper;
import com.cxp.openadmin.domain.dto.backed.CmCodeDto;
import com.cxp.openadmin.domain.dto.backed.SaveCmCodeGrpDto;
import com.cxp.openadmin.service.backed.CommonCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CommonCodeServiceImpl implements CommonCodeService {

    @Resource
    private CmCodeGrpMapper cmCodeGrpMapper;

    @Resource
    private CmCodeMapper cmCodeMapper;

    @Override
    public List<SaveCmCodeGrpDto> getCmCodeGrpList(Map<String, Object> map) {
        return cmCodeGrpMapper.getCmCodeGrpList(map);
    }
    @Override
    public List<CmCodeDto> getCmCodeList(Map<String, Object> map) {
        return cmCodeGrpMapper.getCmCodeList(map);
    }

    @Override
    public int saveCmCodeGrp(List<SaveCmCodeGrpDto> list) {
        int updateRow = 0;
        if(list.size()> 0){
            for(SaveCmCodeGrpDto dto : list){
                if("I".equals(dto.getRow_status())){
                    updateRow += cmCodeGrpMapper.insertCmCodeGrp(dto);
                    //insert
                }else if("U".equals(dto.getRow_status())){
                    updateRow += cmCodeGrpMapper.updateCmCodeGrp(dto);
                    //update
                }else if("D".equals(dto.getRow_status())){
                    updateRow += cmCodeGrpMapper.deleteCmCodeGrp(dto);
                    //delete
                }
            }
        }
        return updateRow;
    }
    public int saveCmCode(List<CmCodeDto> list) {
        int updateRow = 0;
        if(list.size()> 0){
            for(CmCodeDto dto : list){
                if("I".equals(dto.getRow_status())){
                    updateRow += cmCodeGrpMapper.insertCmCode(dto);
                    //insert
                }else if("U".equals(dto.getRow_status())){
                    updateRow += cmCodeGrpMapper.updateCmCode(dto);
                    //update
                }else if("D".equals(dto.getRow_status())){
                    updateRow += cmCodeGrpMapper.deleteCmCode(dto);
                    //delete
                }
            }
        }
        return updateRow;
    }

}
