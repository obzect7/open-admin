package com.cxp.openadmin.service.backed.impl;

import com.cxp.openadmin.dao.TMstWhMapper;
import com.cxp.openadmin.domain.dto.backed.TmstWhDto;
import com.cxp.openadmin.exception.http.ParamException;
import com.cxp.openadmin.service.backed.MstWhService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class MstWhServiceImpl implements MstWhService {

    @Resource
    private TMstWhMapper mstWhMapper;

    @Override
    public List<TmstWhDto> getMstWhList(Map<String, Object> map) {
        return mstWhMapper.getMstWhList(map);
    }

    @Override
    @Transactional
    public int saveMstWh(List<TmstWhDto> list) {
        int updateRow = 0;
        if(list.size()> 0){
            for(TmstWhDto dto : list){
                if(StringUtils.isEmpty(dto.getPlant_cd())){
//                    throw new RuntimeException("사업장코드는 필수입력입니다.");
                    throw new ParamException(1002);
                }
                if("I".equals(dto.getRow_status())){
                    updateRow += mstWhMapper.insertMstWh(dto);
                    //insert
                }else if("U".equals(dto.getRow_status())){
                    updateRow += mstWhMapper.updateMstWh(dto);
                    //update
                }else if("D".equals(dto.getRow_status())){
                    updateRow += mstWhMapper.deleteMstWh(dto);
                    //delete
                }
            }
        }
        return updateRow;
    }

}
