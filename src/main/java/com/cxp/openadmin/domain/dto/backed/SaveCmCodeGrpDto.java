package com.cxp.openadmin.domain.dto.backed;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
public class SaveCmCodeGrpDto {

    private String _row_status;  //row 상태

    @NotBlank(message = "그룹코드는 필수입력입니다.")
    private String group_cd;

    @NotBlank(message = "그룹명은 필수입력입니다.")
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
