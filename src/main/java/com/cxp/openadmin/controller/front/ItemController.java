package com.cxp.openadmin.controller.front;

import com.cxp.openadmin.controller.BaseController;
import com.cxp.openadmin.domain.dto.front.CustomerDto;
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

    @PostMapping("/saveItem")
    public HttpResponse saveItem(@RequestBody List<ItemDto> list, HttpServletRequest request){

        return HttpResponse.success(itemService.saveItem(list));
    }

    /**
     * 파일 그룹 고유번호인 FILE_GRP_SEQ를 미리 업데이트 해준다.
     * @param list
     * @param request
     * @return
     */
    @PostMapping("/saveFileGrpSeq")
    public HttpResponse saveFileGrpSeq(@RequestBody ItemDto item, HttpServletRequest request){
        return HttpResponse.success(itemService.saveFileGrpSeq(item));
    }




}
