package com.cxp.openadmin.service.backed;

import com.cxp.openadmin.domain.dto.backed.CmCodeDto;
import com.cxp.openadmin.domain.dto.backed.SaveCmCodeGrpDto;

import java.util.List;
import java.util.Map;

public interface CommonCodeService {


    List<SaveCmCodeGrpDto> getCmCodeGrpList(Map<String, Object> map);
    List<SaveCmCodeGrpDto> getCmCodeListAllUseStore(Map<String, Object> map);

    List<CmCodeDto> getCmCodeList(Map<String, Object> map);

    int saveCmCodeGrp(List<SaveCmCodeGrpDto> list);

    int saveCmCode(List<CmCodeDto> list);

}
