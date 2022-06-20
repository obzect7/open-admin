package com.cxp.openadmin.controller.backed;

import com.cxp.openadmin.controller.BaseController;
import com.cxp.openadmin.domain.dto.backed.TmstPlantDto;
import com.cxp.openadmin.util.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/backed/plant")
public class MstPlantController extends BaseController {

    @Autowired
    private com.cxp.openadmin.service.backed.MstPlantService mstPlantService;

    @PostMapping("/getMstPlantList")
    public HttpResponse getMstPlantList(@RequestParam(required = false) Map<String, Object> map, HttpServletRequest request){

        return HttpResponse.success(mstPlantService.getMstPlantList(map));
    }

    @PostMapping("/saveMstPlant")
    public HttpResponse saveMstPlant(@RequestBody(required = false) List<TmstPlantDto> list, HttpServletRequest request){
        return HttpResponse.success(mstPlantService.saveMstPlant(list));
    }

}
