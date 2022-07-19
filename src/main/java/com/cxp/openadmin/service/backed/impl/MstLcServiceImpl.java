package com.cxp.openadmin.service.backed.impl;

import com.cxp.openadmin.dao.TMstLcMapper;
import com.cxp.openadmin.domain.dto.backed.TmstLcDto;
import com.cxp.openadmin.service.backed.MstLcService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class MstLcServiceImpl implements MstLcService {

    @Resource
    private TMstLcMapper mstLcMapper;

    @Override
    public List<TmstLcDto> getMstLcList(Map<String, Object> map) {

        if(map != null && map.get("wh_cd") !=null && !"".equals(map.get("wh_cd").toString())){
            String[] whCdArr = map.get("wh_cd").toString().split(",");
            map.put("whArr",whCdArr);
        }
        return mstLcMapper.getMstLcList(map);
    }

    @Override
    public int saveMstLc(List<TmstLcDto> list) {
        int updateRow = 0;
        if(list.size()> 0){
            for(TmstLcDto dto : list){
                if("I".equals(dto.getRow_status())){
                    updateRow += mstLcMapper.insertMstLc(dto);
                    //insert
                }else if("U".equals(dto.getRow_status())){
                    updateRow += mstLcMapper.updateMstLc(dto);
                    //update
                }else if("D".equals(dto.getRow_status())){
                    updateRow += mstLcMapper.deleteMstLc(dto);
                    //delete
                }
            }
        }
        return updateRow;
    }

}
