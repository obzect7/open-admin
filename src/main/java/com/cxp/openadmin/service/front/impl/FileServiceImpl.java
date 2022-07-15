package com.cxp.openadmin.service.front.impl;

import com.cxp.openadmin.dao.FileMapper;
import com.cxp.openadmin.domain.dto.front.FileDto;
import com.cxp.openadmin.service.front.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {

    @Resource
    private FileMapper fileMapper;

    @Override
    public int saveFile(List<FileDto> list) {

        int updateRow = 0;
        if(list.size()> 0){
            for(FileDto dto : list){
                if("I".equals(dto.getRow_status())){
                    updateRow += fileMapper.insertfile(dto);
                }else if("U".equals(dto.getRow_status())){
                    //updateRow += itemMapper.updateItem(dto);
                }else if("D".equals(dto.getRow_status())){
                    //updateRow += itemMapper.deleteItem(dto);
                }
            }
        }
        return updateRow;
    }
}
