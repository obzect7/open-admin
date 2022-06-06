package com.cxp.openadmin.domain.dto.backed;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SaveCmCodeGrpDto {

    private String _rowStatus;  //row 상태
    private String group_cd;
    private String group_nm;
    private String group_nm_en;
    private String code_type;
    private String rem;
    private String use_yn;
    private String reg_id;
    private Timestamp reg_dt;
    private String mod_id;
    private Timestamp mod_dt;
}
