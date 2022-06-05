package com.cxp.openadmin.domain.dto.front;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import io.swagger.annotations.ApiModelProperty;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CommonCodeGrpDto {

    private String rowStatus;

    @ApiModelProperty(value = "코드그룹 CD", example = "USE_YN", required = true)
    private String groupCd;

    private String groupNm;
    private String groupNmEn;
    private String codeType;
    private String rem;

    @ApiModelProperty(value = "사용여부", example = "Y", required = true)
    private String useYn;

    private String regId;
    private Timestamp regDt;
    private String modId;
    private Timestamp modDt;

}
