package com.cxp.openadmin.controller.front;

import com.cxp.openadmin.controller.BaseController;
import com.cxp.openadmin.domain.dto.front.BoardDto;
import com.cxp.openadmin.service.front.BoardService;
import com.cxp.openadmin.util.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/front/board")
public class BoardController extends BaseController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/getBoardList")
    public HttpResponse getBoardList(@RequestParam(required = false) Map<String, Object> map, HttpServletRequest request){
        return HttpResponse.success(boardService.getBoardList(map));
    }

    @PostMapping("/saveItem")
    public HttpResponse saveItem(@RequestBody List<BoardDto> list, HttpServletRequest request){

        return HttpResponse.success(boardService.saveItem(list));
    }




}
