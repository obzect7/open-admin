package com.cxp.openadmin.service.backed.impl;

import com.cxp.openadmin.dao.CmCodeGrpMapper;
import com.cxp.openadmin.dao.RoleMgtMapper;
import com.cxp.openadmin.domain.dto.backed.SaveRoleMenuDto;
import com.cxp.openadmin.domain.dto.backed.SaveRoleMenusDto;
import com.cxp.openadmin.domain.dto.backed.SaveRoleMgtDto;
import com.cxp.openadmin.service.backed.RoleMgtService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleMgtServiceImpl implements RoleMgtService {

    @Resource
    private RoleMgtMapper mapper;

    @Override
    public List<SaveRoleMgtDto> getRoleList(Map<String, Object> map) {
        return mapper.getRoleList(map);
    }

    @Override
    public List<SaveRoleMenuDto> getRoleMenuList(Map<String, Object> map) {
        return mapper.getRoleMenuList(map);
    }

    @Override
    public int saveRoleList(List<SaveRoleMgtDto> list) {
        int updateRow = 0;
        if(list.size()> 0){
            for(SaveRoleMgtDto dto : list){
                if("I".equals(dto.getRow_status())){
                    updateRow += mapper.insertRoleList(dto);
                    //insert
                }else if("U".equals(dto.getRow_status())){
                    updateRow += mapper.updateRoleList(dto);
                    //update
                }else if("D".equals(dto.getRow_status())){
                    updateRow += mapper.deleteRoleList(dto);
                    //delete
                }
            }

        }
        return updateRow;
    }

    @Override
    public int saveRoleMenuList(List<SaveRoleMenuDto> list) {
        int updateRow = 0;
        SaveRoleMenuDto param = new SaveRoleMenuDto();

        if(list.size()> 0){

            int cnt = 0;
            String menuIds = "";
            for(SaveRoleMenuDto dto : list) {
                if (cnt == 0) {
                    menuIds = String.valueOf(dto.getMenu_id());
                } else {
                    menuIds += "," + String.valueOf(dto.getMenu_id());
                }
                cnt++;
            }

            param.setMenu_ids(menuIds);
            param.setRole_id(list.get(0).getRole_id());
            param.setId(list.get(0).getId());

            mapper.saveRoleMenuList(param);
        }
        return updateRow;
    }
}
