package com.cxp.openadmin.controller.backed;

import com.cxp.openadmin.controller.BaseController;
import com.cxp.openadmin.domain.dto.backed.SaveMenuMgtDto;
import com.cxp.openadmin.domain.dto.backed.SaveRoleMenuDto;
import com.cxp.openadmin.domain.dto.backed.SaveRoleMgtDto;
import com.cxp.openadmin.service.backed.MenuMgtService;
import com.cxp.openadmin.service.backed.RoleMgtService;
import com.cxp.openadmin.util.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/backed/rolemgt")
public class RoleMgtController extends BaseController {

    @Autowired
    private RoleMgtService roleMgtService;

    @PostMapping("/getRoleList")
    public HttpResponse getRoleList(@RequestParam(required = false) Map<String, Object> map, HttpServletRequest request){
        return HttpResponse.success(roleMgtService.getRoleList(map));
    }

    @PostMapping("/saveRoleList")
    public HttpResponse saveRoleList(@RequestBody(required = false) List<SaveRoleMgtDto> list, HttpServletRequest request){
        return HttpResponse.success(roleMgtService.saveRoleList(list));
    }

    @PostMapping("/getRoleMenuList")
    public HttpResponse getRoleMenusList(@RequestParam(required = false) Map<String, Object> map, HttpServletRequest request) {
        return HttpResponse.success(roleMgtService.getRoleMenuList(map));
    }

    @PostMapping("/saveRoleMenuList")
    public HttpResponse saveRoleMenuList(@RequestBody(required = false) List<SaveRoleMenuDto> list, HttpServletRequest request){
        return HttpResponse.success(roleMgtService.saveRoleMenuList(list));
    }
}
