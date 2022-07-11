package com.cxp.openadmin.service.backed;

import com.cxp.openadmin.domain.dto.backed.TmstPlantDto;

import java.util.List;
import java.util.Map;

public interface FileCommonService {


    List<TmstPlantDto> getMstPlantList(Map<String, Object> map);

    int saveMstPlant(List<TmstPlantDto> list);

}
