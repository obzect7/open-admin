package com.cxp.openadmin.domain.dto.backed;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Data
@Getter
@Setter
public class TmstPlantDto {

    private String row_status;  //row 상태
    private String plant_cd;
    private String plant_nm;
    private String addr;
    private String use_yn;
    private String remark;
    private String reg_id;
    private Timestamp reg_dt;
    private String mod_id;
    private Timestamp mod_dt;
    private String cm_code_list;
}
