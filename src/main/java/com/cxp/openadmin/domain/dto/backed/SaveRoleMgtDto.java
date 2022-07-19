package com.cxp.openadmin.domain.dto.backed;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SaveRoleMgtDto {

    private String row_status;  //row 상태

    private Integer id;
    private String unique_key;
    private String name;
    private String remark;

}
