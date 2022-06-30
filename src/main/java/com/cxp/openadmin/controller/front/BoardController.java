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

    // 게시판 조회
    @PostMapping("/getBoardList")
    public HttpResponse getBoardList(@RequestParam(required = false) Map<String, Object> map, HttpServletRequest request){
        return HttpResponse.success(boardService.getBoardList(map));
    }

    // 게시판 CRUD
    @PostMapping("/saveBoard")
    public HttpResponse saveBoard(@ModelAttribute BoardDto dto, HttpServletRequest request){
        return HttpResponse.success(boardService.saveBoard(dto));
        //return HttpResponse.success(boardService.updateBoard(list));
    }

    // 댓글 조회
    @PostMapping("/getBoardComtList")
    public HttpResponse getBoardComtList(@RequestParam(required = false) Map<String, Object> map, HttpServletRequest request){
        return HttpResponse.success(boardService.getBoardComtList(map));
    }
}
