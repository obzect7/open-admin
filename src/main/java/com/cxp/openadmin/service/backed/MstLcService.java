package com.cxp.openadmin.service.backed;

import com.cxp.openadmin.domain.dto.backed.TmstLcDto;

import java.util.List;
import java.util.Map;

public interface MstLcService {


    List<TmstLcDto> getMstLcList(Map<String, Object> map);

    int saveMstLc(List<TmstLcDto> list);

}
