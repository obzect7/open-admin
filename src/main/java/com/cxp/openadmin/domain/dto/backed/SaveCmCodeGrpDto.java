package com.cxp.openadmin.domain.dto.backed;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SaveCmCodeGrpDto {

    private String groupCd;
    private String groupNm;
    private String groupNmEn;
    private String codeType;
    private String rem;
    private String useYn;
    private String regId;
    private Timestamp regDt;
    private String modId;
    private Timestamp modDt;
}
