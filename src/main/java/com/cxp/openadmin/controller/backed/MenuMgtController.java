package com.cxp.openadmin.controller.backed;

import com.cxp.openadmin.controller.BaseController;
import com.cxp.openadmin.domain.dto.backed.CmCodeDto;
import com.cxp.openadmin.domain.dto.backed.SaveCmCodeGrpDto;
import com.cxp.openadmin.domain.dto.backed.SaveMenuMgtDto;
import com.cxp.openadmin.service.backed.MenuMgtService;
import com.cxp.openadmin.util.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/backed/menumgt")
public class MenuMgtController extends BaseController {

    @Autowired
    private MenuMgtService menuMgtService;

    @PostMapping("/getMenuList")
    public HttpResponse getMenuList(@RequestParam(required = false) Map<String, Object> map, HttpServletRequest request){
        return HttpResponse.success(menuMgtService.getMenuList(map));
    }

    @PostMapping("/saveMenuList")
    public HttpResponse saveCmCodeGrp(@RequestBody(required = false) List<SaveMenuMgtDto> list, HttpServletRequest request){
        return HttpResponse.success(menuMgtService.saveSaveMenuList(list));
    }
}
