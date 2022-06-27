package com.cxp.openadmin.service.backed;

import com.cxp.openadmin.domain.dto.backed.TmstWhDto;

import java.util.List;
import java.util.Map;

public interface MstWhService {


    List<TmstWhDto> getMstWhList(Map<String, Object> map);

    int saveMstWh(List<TmstWhDto> list);

}
