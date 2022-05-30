package com.cxp.openadmin.domain.dto.front;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CommonCodeDto {

    private String rowStatus;
    private String groupCd;
    private String groupNm;
    private String code;
    private String code2;
    private String codeNm;
    private String codeNm2;
    private String codeNmEn;
    private String data1;
    private String data2;
    private String data3;
    private String data4;
    private String data5;
    private String data6;
    private String data7;
    private String data8;
    private String data9;
    private String data10;
    private String rem;
    private String useYn;
    private String sort;
    private String regId;
    private Timestamp regDt;
    private String modId;
    private Timestamp modDt;

}
