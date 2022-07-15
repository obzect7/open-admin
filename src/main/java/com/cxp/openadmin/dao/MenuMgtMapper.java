package com.cxp.openadmin.dao;

import com.cxp.openadmin.domain.dto.backed.CmCodeDto;
import com.cxp.openadmin.domain.dto.backed.SaveCmCodeGrpDto;
import com.cxp.openadmin.domain.dto.backed.SaveMenuMgtDto;
import com.cxp.openadmin.domain.entity.CmCodeGrp;
import com.cxp.openadmin.domain.entity.SysMenu;
import com.cxp.openadmin.domain.vo.backed.SysMenuVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface MenuMgtMapper extends Mapper<SysMenu> {

    List<SaveMenuMgtDto> getMenuList(@Param("paramsMap") Map<String, Object> map);

    List<SaveCmCodeGrpDto> getCmCodeGrpList(@Param("paramsMap") Map<String, Object> map);

    List<SaveCmCodeGrpDto> getCmCodeListAllUseStore(@Param("paramsMap") Map<String, Object> map);
    List<CmCodeDto> getCmCodeList(@Param("paramsMap") Map<String, Object> map);

    int insertMenuList(@Param("paramsMap") SaveMenuMgtDto dto);
    int updateMenuList(@Param("paramsMap") SaveMenuMgtDto dto);
    int deleteMenuList(@Param("paramsMap") SaveMenuMgtDto dto);

}