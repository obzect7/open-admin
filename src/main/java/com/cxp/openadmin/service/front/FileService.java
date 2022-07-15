package com.cxp.openadmin.service.front;

import com.cxp.openadmin.domain.dto.front.FileDto;

import java.util.List;
import java.util.Map;

public interface FileService {

    //List<ItemDto> getItemList(Map<String, Object> map);

    int saveFile(List<FileDto> list);


}
