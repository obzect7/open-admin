package com.cxp.openadmin.controller.backed;

import com.cxp.openadmin.controller.BaseController;
import com.cxp.openadmin.domain.dto.backed.CmCodeDto;
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

    //store 에서 사용할 형태의 전체 공통코드를 가져온다. 22.06.11
    @PostMapping("/getCmCodeListAllUseStore")
    public HttpResponse getCmCodeListAllUseStore(@RequestParam(required = false) Map<String, Object> map, HttpServletRequest request){

        return HttpResponse.success(commonCodeService.getCmCodeListAllUseStore(map));
    }

    @PostMapping("/getCmCodeList")
    public HttpResponse getCmCodeList(@RequestParam(required = false) Map<String, Object> map, HttpServletRequest request){

        return HttpResponse.success(commonCodeService.getCmCodeList(map));

    }

    @PostMapping("/getCmCodeLoad")
    public HttpResponse getCmCodeLoad(@RequestBody(required = false) Map<String, Object> map, HttpServletRequest request){
        return HttpResponse.success(commonCodeService.getCmCodeList(map));
    }

    @PostMapping("/saveCmCodeGrp")
    public HttpResponse saveCmCodeGrp(@RequestBody(required = false) List<SaveCmCodeGrpDto> list, HttpServletRequest request){
        return HttpResponse.success(commonCodeService.saveCmCodeGrp(list));
    }
    @PostMapping("/saveCmCode")
    public HttpResponse saveCmCode(@RequestBody List<CmCodeDto> list){
        return HttpResponse.success(commonCodeService.saveCmCode(list));
    }

}
