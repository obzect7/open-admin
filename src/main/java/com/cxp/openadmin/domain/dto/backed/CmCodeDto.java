package com.cxp.openadmin.domain.dto.backed;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
public class CmCodeDto {

    private String _row_status;  //row status 컬럼

    @NotBlank(message = "그룹코드는 필수입력입니다.")
    private String group_cd;

    @NotBlank(message = "코드ID는 필수입력입니다.")
    private String code;

    private String code_nm;
    private String code_nm_en;
    private int sort;
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
    private String use_yn;
    private String reg_id;
    private Timestamp reg_dt;
    private String mod_id;
    private Timestamp mod_dt;
}
