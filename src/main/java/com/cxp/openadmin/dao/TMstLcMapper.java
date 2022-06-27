package com.cxp.openadmin.dao;

import com.cxp.openadmin.domain.dto.backed.TmstLcDto;
import com.cxp.openadmin.domain.entity.TMstLc;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface TMstLcMapper extends Mapper<TMstLc> {
    List<TmstLcDto> getMstLcList(@Param("paramsMap") Map<String, Object> map);

    int insertMstLc(@Param("paramsMap") TmstLcDto dto);
    int updateMstLc(@Param("paramsMap") TmstLcDto dto);
    int deleteMstLc(@Param("paramsMap") TmstLcDto dto);
}