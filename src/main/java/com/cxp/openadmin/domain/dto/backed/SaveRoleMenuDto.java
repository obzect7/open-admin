package com.cxp.openadmin.domain.dto.backed;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SaveRoleMenuDto {

    private String row_status;  //row 상태

    private String menu_id;
    private String path;
    private String invisible;
    private Integer parent_id;
    private String name;
    private String icon;
    private String cache_able;
    private Integer sort;
    private String component;
    private String permissions;
    private Integer col_nm;

    private Integer id;
    private Integer role_id;
    private String menu_ids;

}
