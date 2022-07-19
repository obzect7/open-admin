package com.cxp.openadmin.service.backed.impl;

import com.cxp.openadmin.dao.FileMapper;
import com.cxp.openadmin.dao.TMstPlantMapper;
import com.cxp.openadmin.domain.dto.backed.CmFileMstDto;
import com.cxp.openadmin.domain.dto.backed.TmstPlantDto;
import com.cxp.openadmin.service.backed.FileService;
import com.cxp.openadmin.service.backed.MstPlantService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {

    @Resource
    private FileMapper fileMapper;

    @Override
    public List<CmFileMstDto> getCmFileList(Map<String, Object> map) {
        return fileMapper.getCmFileList(map);
    }

    @Override
    public int saveCmFileMst(CmFileMstDto dto) {
        int updateRow = 0;
            updateRow += fileMapper.insertCmFileMst(dto);
            //insert
        return updateRow;
    }

    @Override
    public int deleteFile(Map<String, Object> map) {
        return fileMapper.deleteFile(map);
    }

}
