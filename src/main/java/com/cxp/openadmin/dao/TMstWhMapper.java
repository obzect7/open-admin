package com.cxp.openadmin.dao;

import com.cxp.openadmin.domain.dto.backed.TmstWhDto;
import com.cxp.openadmin.domain.entity.TMstWh;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface TMstWhMapper extends Mapper<TMstWh> {
    List<TmstWhDto> getMstWhList(@Param("paramsMap") Map<String, Object> map);

    int insertMstWh(@Param("paramsMap") TmstWhDto dto);
    int updateMstWh(@Param("paramsMap") TmstWhDto dto);
    int deleteMstWh(@Param("paramsMap") TmstWhDto dto);
}