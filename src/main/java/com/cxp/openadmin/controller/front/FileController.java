package com.cxp.openadmin.controller.front;

import com.cxp.openadmin.controller.BaseController;
import com.cxp.openadmin.domain.dto.front.FileDto;

import com.cxp.openadmin.service.front.FileService;
import com.cxp.openadmin.util.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/front/file")
public class FileController extends BaseController {

    @Autowired
    private FileService fileService;

    @PostMapping("/saveFile")
    public HttpResponse saveFile(@RequestBody List<FileDto> list, HttpServletRequest request){

        return HttpResponse.success(fileService.saveFile(list));
    }




}
