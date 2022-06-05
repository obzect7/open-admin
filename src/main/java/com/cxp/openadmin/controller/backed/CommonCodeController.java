package com.cxp.openadmin.controller.backed;

import com.cxp.openadmin.controller.BaseController;
import com.cxp.openadmin.domain.dto.backed.*;
import com.cxp.openadmin.domain.entity.SysUser;
import com.cxp.openadmin.exception.http.ParamException;
import com.cxp.openadmin.service.backed.CommonCodeService;
import com.cxp.openadmin.service.backed.SystemService;
import com.cxp.openadmin.util.HttpResponse;
import com.cxp.openadmin.util.PagedGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/backed/commoncode")
public class CommonCodeController extends BaseController {

    @Autowired
    private CommonCodeService commonCodeService;

    @PostMapping("/getCmCodeGrpList")
    public HttpResponse getCmCodeGrpList(@RequestBody(required = false) Map<String, Object> map, HttpServletRequest request){

        commonCodeService.getCmCodeGrpList(map);
        return HttpResponse.success();
    }


}
