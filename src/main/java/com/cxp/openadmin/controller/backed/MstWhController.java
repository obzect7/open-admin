package com.cxp.openadmin.controller.backed;

import com.cxp.openadmin.controller.BaseController;
import com.cxp.openadmin.domain.dto.backed.TmstWhDto;
import com.cxp.openadmin.util.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/backed/wh")
public class MstWhController extends BaseController {

    @Autowired
    private com.cxp.openadmin.service.backed.MstWhService mstWhService;

    @PostMapping("/getMstWhList")
    public HttpResponse getMstWhList(@RequestParam(required = false) Map<String, Object> map, HttpServletRequest request){

        return HttpResponse.success(mstWhService.getMstWhList(map));
    }
    @PostMapping("/getWhComboList")
    public HttpResponse getWhComboList(@RequestBody(required = false) Map<String, Object> map, HttpServletRequest request){
        return HttpResponse.success(mstWhService.getMstWhList(map));
    }

    @PostMapping("/saveMstWh")
    public HttpResponse saveMstWh(@Valid @RequestBody List<TmstWhDto> list, HttpServletRequest request){
        return HttpResponse.success(mstWhService.saveMstWh(list));
    }
}
