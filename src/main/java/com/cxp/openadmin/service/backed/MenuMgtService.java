package com.cxp.openadmin.service.backed;

import com.cxp.openadmin.domain.dto.backed.CmCodeDto;
import com.cxp.openadmin.domain.dto.backed.SaveCmCodeGrpDto;
import com.cxp.openadmin.domain.dto.backed.SaveMenuMgtDto;
import com.cxp.openadmin.domain.vo.backed.SysMenuVo;

import java.util.List;
import java.util.Map;

public interface MenuMgtService {

    List<SaveMenuMgtDto> getMenuList(Map<String, Object> map);

    int saveSaveMenuList(List<SaveMenuMgtDto> list);

}
