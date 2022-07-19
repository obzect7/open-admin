package com.cxp.openadmin.service.backed.impl;

import com.cxp.openadmin.dao.TMstPlantMapper;
import com.cxp.openadmin.domain.dto.backed.TmstPlantDto;
import com.cxp.openadmin.exception.http.ParamException;
import com.cxp.openadmin.service.backed.MstPlantService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLIntegrityConstraintViolationException;
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
    @Transactional(rollbackFor = {Exception.class})
    /*
     *  @Transactional 어노테이션 설명
     *  Spring에서 @Transactional 선언 시 기본 속성은 아래와 같다
     *  @Transactional(rollbackFor = {RuntimeException.class, Error.class}) 기본 속성
     *  즉, RuntimeException과 Error가 발생했을 경우에는 기본적으로 rollback이 됨
     *  위 두 가지 경우가 아니라면 rollback이 되지 않는다.  하지만 트랜잭션을 rollback 하는 다른 방법들도 있다.
     *  @Transactional(rollbackFor = {Exception.class})
     *  위와 같이 작성하면 모든 예외에 대해서 rollback 하게 된다. 옵션을 변경해서 rollback이 가능
     */
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
