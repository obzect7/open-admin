package com.cxp.openadmin.dao;

import com.cxp.openadmin.domain.dto.backed.SaveCmCodeGrpDto;
import com.cxp.openadmin.domain.entity.CmCodeGrp;
import com.cxp.openadmin.domain.vo.backed.SysRoleMenuPermissionVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface CmCodeGrpMapper extends Mapper<CmCodeGrp> {

    List<SaveCmCodeGrpDto> getCmCodeGrpList(@Param("paramsMap") Map<String, Object> map);
}