package com.cxp.openadmin.controller;

import com.cxp.openadmin.domain.dto.backed.CmFileMstDto;
import com.cxp.openadmin.exception.http.UploadException;
import com.cxp.openadmin.util.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.http.entity.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("common")
public class CommonController {

    @Autowired
    private Environment env;

    @Autowired
    private com.cxp.openadmin.service.backed.FileService fileService;

    /**
     * file_grp_seq 를 파라미터로 파일리스트 가져오기
     * author : mslee
     * @param map
     * @param request
     * @return
     */
    @PostMapping("/getCmFileList")
    public HttpResponse getCmFileList(@RequestParam(required = false) Map<String, Object> map, HttpServletRequest request){
        return HttpResponse.success(fileService.getCmFileList(map));
    }

    /**
     * 1건 파일 업로드
     * @param file
     * @param category
     * @return
     */
    @PostMapping("/upload")
    public HttpResponse upload(@RequestParam("file") MultipartFile file, String category){

        String fileSpace = env.getProperty("resource.upload-location");
        String fileServerUrl = env.getProperty("resource.server-url");
        String fileName = file.getOriginalFilename();
        String fileNameArr[] = fileName.split("\\.");
        String suffix = fileNameArr[fileNameArr.length - 1];
        String newFileName = UUID.randomUUID().toString() + "." + suffix;
        String finalFacePath = fileSpace + File.separator + category + File.separator + newFileName;
        File dist = new File(finalFacePath);
        if (dist.getParentFile() != null) {
            // 폴더 생성
            dist.getParentFile().mkdirs();
        }
        dist.setReadable(true);
        try {
            file.transferTo(dist);
        } catch (IOException e) {
            log.error("upload error {}",e.getMessage());
            throw new UploadException(1008);
        }
        return HttpResponse.success(fileServerUrl + '/' + category + '/' + newFileName);
    }


    @PostMapping("/uploads")
    public HttpResponse uploads(
            @RequestParam("files") MultipartFile file,
            @RequestParam("file_grp_seq") String file_grp_seq,
            @RequestParam("file_size") String file_size,
            @RequestParam("file_type") String file_type,
            @RequestParam("file_uid") String file_uid
    ){


        String fileSpace = env.getProperty("resource.upload-location");
        String fileServerUrl = env.getProperty("resource.server-url");
        String fileName = file.getOriginalFilename();
        String fileNameArr[] = fileName.split("\\.");
        String suffix = fileNameArr[fileNameArr.length - 1];
        String newFileName = UUID.randomUUID().toString() + "." + suffix;
//        String finalFacePath = fileSpace + File.separator + "upload2" + File.separator + newFileName;
        String finalFacePath = fileSpace + File.separator + File.separator + newFileName;
        File dist = new File(finalFacePath);
        if (dist.getParentFile() != null) {
            // 폴더 생성
            dist.getParentFile().mkdirs();
        }
        dist.setReadable(true);
        try {
            file.transferTo(dist);

            CmFileMstDto dto = new CmFileMstDto();
            dto.setFile_grp_seq(file_grp_seq);
            dto.setFile_no(file_uid);
            dto.setFile_nm(newFileName);
            dto.setFile_type(suffix);
            dto.setFile_size(new BigDecimal(file_size));
            dto.setFile_path(finalFacePath);
//            dto.setSort_order();
            dto.setOrgin_file_nm(fileName);
            dto.setSave_type("F");
            dto.setUse_yn("Y");

            fileService.saveCmFileMst(dto);
        } catch (IOException e) {
            log.error("upload error {}",e.getMessage());
            throw new UploadException(1008);
        }


        return HttpResponse.success("");
    }

    @CrossOrigin( origins = "*", exposedHeaders = {"Content-Disposition"}, maxAge = 3600)
    @RequestMapping(value = "/filedown", method= {RequestMethod.POST, RequestMethod.GET})
    public void fileDown (
            @RequestBody CmFileMstDto dto,
//            @RequestParam("file_size") String file_size,
            HttpServletResponse response) throws Exception {

        String fileSpace = env.getProperty("resource.upload-location");
        String fileFullPath = fileSpace + File.separator + File.separator + dto.getFile_nm();

        try{
            Path filePath = Paths.get(fileFullPath);

            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; fileName=" + URLEncoder.encode(dto.getFile_nm(),"UTF-8"));
            response.setHeader("Content-Transfer-Encoding", "binary");
             response.setHeader( "Access-Control-Allow-Headers","Content-Disposition");
            response.setHeader( "Access-Control-Expose-Headers","Content-Disposition");
            byte[] fileByte = FileUtils.readFileToByteArray(new File(fileFullPath));
            response.getOutputStream().write(fileByte);
            response.getOutputStream().flush();
            response.getOutputStream().close();

        } catch(Exception e){

        }
    }

    /**
     * FILE_GRP_SEQ 를 생성하는 함수.
     * @param map
     * @param request
     * @return
     */
    @PostMapping("/getFileGrpSeq")
    public HttpResponse getFileGrpSeq(@RequestParam(required = false) Map<String, Object> map, HttpServletRequest request){
        String fileGrpSeq = UUID.randomUUID().toString();
        return HttpResponse.success(fileGrpSeq);
    }

    /**
     * 파일 삭제함수 호출
     * author : mslee
     * @param map
     * @param request
     * @return
     */
    @PostMapping("/deleteFile")
    public HttpResponse deleteFile(@RequestParam(required = false) Map<String, Object> map, HttpServletRequest request){
        return HttpResponse.success(fileService.deleteFile(map));
    }

}
