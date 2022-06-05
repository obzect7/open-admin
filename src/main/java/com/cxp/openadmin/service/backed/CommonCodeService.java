package com.cxp.openadmin.service.backed;

import com.cxp.openadmin.domain.dto.backed.*;
import com.cxp.openadmin.domain.entity.CmCodeGrp;
import com.cxp.openadmin.domain.entity.SysMenu;
import com.cxp.openadmin.domain.entity.SysRole;
import com.cxp.openadmin.domain.entity.SysUser;
import com.cxp.openadmin.domain.vo.backed.RoleMenuAndMenuPermissionVo;
import com.cxp.openadmin.domain.vo.backed.SysMenuVo;
import com.cxp.openadmin.domain.vo.backed.SysRoleMenuPermissionVo;
import com.cxp.openadmin.util.PagedGridResult;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CommonCodeService {


    List<SaveCmCodeGrpDto> getCmCodeGrpList(Map<String, Object> map);

    void saveCmCodeGrp(SaveCmCodeGrpDto saveCmCodeGrpDto);

}
