package com.cxp.openadmin.dao;

import com.cxp.openadmin.domain.dto.backed.CmCodeDto;
import com.cxp.openadmin.domain.dto.backed.SaveCmCodeGrpDto;
import com.cxp.openadmin.domain.entity.CmCodeGrp;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface CmCodeGrpMapper extends Mapper<CmCodeGrp> {

    List<SaveCmCodeGrpDto> getCmCodeGrpList(@Param("paramsMap") Map<String, Object> map);

    List<SaveCmCodeGrpDto> getCmCodeListAllUseStore(@Param("paramsMap") Map<String, Object> map);
    List<CmCodeDto> getCmCodeList(@Param("paramsMap") Map<String, Object> map);
    int insertCmCodeGrp(@Param("paramsMap") SaveCmCodeGrpDto dto);
    int updateCmCodeGrp(@Param("paramsMap") SaveCmCodeGrpDto dto);
    int deleteCmCodeGrp(@Param("paramsMap") SaveCmCodeGrpDto dto);

    int insertCmCode(@Param("paramsMap") CmCodeDto dto);
    int updateCmCode(@Param("paramsMap") CmCodeDto dto);
    int deleteCmCode(@Param("paramsMap") CmCodeDto dto);

}