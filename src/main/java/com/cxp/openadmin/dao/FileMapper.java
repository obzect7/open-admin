package com.cxp.openadmin.dao;

import com.cxp.openadmin.domain.dto.backed.CmFileMstDto;
import com.cxp.openadmin.domain.dto.backed.TmstPlantDto;
import com.cxp.openadmin.domain.entity.TMstPlant;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface FileMapper extends Mapper<TMstPlant> {
    List<CmFileMstDto> getCmFileList(@Param("paramsMap") Map<String, Object> map);

    int insertCmFileMst(@Param("paramsMap") CmFileMstDto dto);

    int deleteFile(@Param("paramsMap") Map<String, Object> map);
}