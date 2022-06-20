package com.cxp.openadmin.dao;

import com.cxp.openadmin.domain.dto.backed.TmstPlantDto;
import com.cxp.openadmin.domain.entity.TMstPlant;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface TMstPlantMapper extends Mapper<TMstPlant> {
    List<TmstPlantDto> getMstPlantList(@Param("paramsMap") Map<String, Object> map);

    int insertMstPlant(@Param("paramsMap") TmstPlantDto dto);
    int updateMstPlant(@Param("paramsMap") TmstPlantDto dto);
    int deleteMstPlant(@Param("paramsMap") TmstPlantDto dto);
}