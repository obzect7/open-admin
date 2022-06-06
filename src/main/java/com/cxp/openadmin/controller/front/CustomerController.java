package com.cxp.openadmin.controller.front;

import com.cxp.openadmin.controller.BaseController;
import com.cxp.openadmin.service.front.CustomerService;
import com.cxp.openadmin.util.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/front/customer")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/getCustomerList")
    public HttpResponse getCustomerList(@RequestParam(required = false) Map<String, Object> map, HttpServletRequest request){
        return HttpResponse.success(customerService.getCustomerList(map));
    }


}
