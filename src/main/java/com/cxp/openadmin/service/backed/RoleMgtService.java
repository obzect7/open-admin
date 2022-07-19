package com.cxp.openadmin.service.backed;

import com.cxp.openadmin.domain.dto.backed.SaveRoleMenuDto;
import com.cxp.openadmin.domain.dto.backed.SaveRoleMgtDto;

import java.util.List;
import java.util.Map;

public interface RoleMgtService {

    List<SaveRoleMgtDto> getRoleList(Map<String, Object> map);

    List<SaveRoleMenuDto> getRoleMenuList(Map<String, Object> map);

    int saveRoleList(List<SaveRoleMgtDto> list);

    int saveRoleMenuList(List<SaveRoleMenuDto> list);

}
