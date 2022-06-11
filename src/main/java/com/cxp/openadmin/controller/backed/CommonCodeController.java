package com.cxp.openadmin.controller.backed;

import com.cxp.openadmin.controller.BaseController;
import com.cxp.openadmin.domain.dto.backed.SaveCmCodeGrpDto;
import com.cxp.openadmin.service.backed.CommonCodeService;
import com.cxp.openadmin.util.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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

    @PostMapping("/saveCmCodeGrp")
    public HttpResponse saveCmCodeGrp(@RequestBody(required = false) List<SaveCmCodeGrpDto> list, HttpServletRequest request){

//        list.size();
        return HttpResponse.success(commonCodeService.saveCmCodeGrp(list));
    }

}
