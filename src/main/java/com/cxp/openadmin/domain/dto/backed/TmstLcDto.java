package com.cxp.openadmin.domain.dto.backed;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Data
@Getter
@Setter
public class TmstLcDto {

    private String row_status;  //row 상태
    private String plant_cd;
    private String plant_nm;
    private String wh_cd;
    private String wh_nm;
    private String lc_cd;
    private String lc_nm;
    private String zn_cd;
    private String lc_holdtype;
    private String lc_type;
    private String use_yn;
    private String remark;
    private String reg_id;
    private Timestamp reg_dt;
    private String mod_id;
    private Timestamp mod_dt;
}
