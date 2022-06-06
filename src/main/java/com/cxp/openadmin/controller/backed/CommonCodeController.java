package com.cxp.openadmin.controller.backed;

import com.cxp.openadmin.controller.BaseController;
import com.cxp.openadmin.service.backed.CommonCodeService;
import com.cxp.openadmin.util.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/backed/commoncode")
public class CommonCodeController extends BaseController {

    @Autowired
    private CommonCodeService commonCodeService;

    @PostMapping("/getCmCodeGrpList")
    public HttpResponse getCmCodeGrpList(@RequestParam(required = false) Map<String, Object> map, HttpServletRequest request){

        return HttpResponse.success(commonCodeService.getCmCodeGrpList(map));
    }

    @PostMapping("/getCmCodeList")
    public HttpResponse getCmCodeList(@RequestParam(required = false) Map<String, Object> map, HttpServletRequest request){

        return HttpResponse.success(commonCodeService.getCmCodeList(map));
    }
}
