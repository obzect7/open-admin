package com.cxp.openadmin.controller.backed;

import com.cxp.openadmin.controller.BaseController;
import com.cxp.openadmin.domain.dto.backed.TmstLcDto;
import com.cxp.openadmin.util.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/backed/lc")
public class MstLcController extends BaseController {

    @Autowired
    private com.cxp.openadmin.service.backed.MstLcService mstLcService;

    @PostMapping("/getMstLcList")
    public HttpResponse getMstPlantList(@RequestParam(required = false) Map<String, Object> map, HttpServletRequest request){

        return HttpResponse.success(mstLcService.getMstLcList(map));
    }

    @PostMapping("/saveMstLc")
    public HttpResponse saveMstPlant(@RequestBody(required = false) List<TmstLcDto> list, HttpServletRequest request){
        return HttpResponse.success(mstLcService.saveMstLc(list));
    }
}
