package com.cxp.openadmin.service.backed;

import com.cxp.openadmin.domain.dto.backed.CmFileMstDto;
import com.cxp.openadmin.domain.dto.backed.TmstPlantDto;

import java.util.List;
import java.util.Map;

public interface FileService {


    List<CmFileMstDto> getCmFileList(Map<String, Object> map);

    int saveCmFileMst(CmFileMstDto dto);


    int deleteFile(Map<String, Object> map);

}
