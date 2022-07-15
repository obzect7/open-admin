package com.cxp.openadmin.domain.dto.backed;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Data
@Getter
@Setter
public class SaveMenuMgtDto {

    private String row_status;  //row 상태

    private Integer id;
    private String path;
    private String invisible;
    private Integer parent_id;
    private String name;
    private String icon;
    private String cache_able;
    private Integer sort;
    private String component;
    private String permissions;

}
