package com.cxp.openadmin.dao;

import com.cxp.openadmin.domain.dto.backed.*;
import com.cxp.openadmin.domain.entity.SysMenu;
import com.cxp.openadmin.domain.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface RoleMgtMapper extends Mapper<SysRole> {

    List<SaveRoleMgtDto> getRoleList(@Param("paramsMap") Map<String, Object> map);

    List<SaveRoleMenuDto> getRoleMenuList(@Param("paramsMap") Map<String, Object> map);

    int insertRoleList(@Param("paramsMap") SaveRoleMgtDto dto);
    int updateRoleList(@Param("paramsMap") SaveRoleMgtDto dto);
    int deleteRoleList(@Param("paramsMap") SaveRoleMgtDto dto);
    int saveRoleMenuList(@Param("paramsMap") SaveRoleMenuDto dto);

}