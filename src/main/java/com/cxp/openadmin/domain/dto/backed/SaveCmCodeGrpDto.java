package com.cxp.openadmin.domain.dto.backed;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SaveCmCodeGrpDto {

    private String GroupCd;
    private String GroupNm;
    private String GroupNmEn;
    private String codeType;
    private String rem;
    private String useYn;
    private String regId;
    private Timestamp regDt;
    private String modId;
    private Timestamp modDt;
}
