package com.cxp.openadmin.domain.dto.backed;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
@Getter
@Setter
public class TmstWhDto {

    private String row_status;  //row 상태

    @NotBlank(message = "사업장코드는 필수입력 입니다.")
    private String plant_cd;

    private String wh_cd;
    private String wh_nm;
    private String wh_type;
    private String use_yn;
    private String remark;
    private String reg_id;
    private Timestamp reg_dt;
    private String mod_id;
    private Timestamp mod_dt;
}
