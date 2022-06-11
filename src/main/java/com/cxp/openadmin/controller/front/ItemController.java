package com.cxp.openadmin.controller.front;

import com.cxp.openadmin.controller.BaseController;
import com.cxp.openadmin.service.front.ItemService;
import com.cxp.openadmin.util.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cxp.openadmin.domain.dto.front.ItemDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/front/item")
public class ItemController extends BaseController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/getItemList")
    public HttpResponse getItemList(@RequestParam(required = false) Map<String, Object> map, HttpServletRequest request){
        return HttpResponse.success(itemService.getItemList(map));
    }

//    @PostMapping("/saveCustomer")
//    public HttpResponse saveCustomer(@RequestBody List<CustomerDto> list, HttpServletRequest request){
//
//        return HttpResponse.success(itemService.saveCustomer(list));
//    }


}
