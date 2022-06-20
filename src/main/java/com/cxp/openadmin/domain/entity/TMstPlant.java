package com.cxp.openadmin.domain.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "t_mst_plant")
public class TMstPlant {
    /**
     * 사업장코드
     */
    @Column(name = "PLANT_CD")
    private String plantCd;

    /**
     * 사업장_명칭
     */
    @Column(name = "PLANT_NM")
    private String plantNm;

    /**
     * 주소
     */
    @Column(name = "ADDR")
    private String addr;

    /**
     * 사용여부
     */
    @Column(name = "USE_YN")
    private String useYn;

    /**
     * 비고사항
     */
    @Column(name = "REMARK")
    private String remark;

    /**
     * 등록자id
     */
    @Column(name = "REG_ID")
    private String regId;

    /**
     * 등록일시
     */
    @Column(name = "REG_DT")
    private Date regDt;

    /**
     * 수정자id
     */
    @Column(name = "MOD_ID")
    private String modId;

    /**
     * 수정일시
     */
    @Column(name = "MOD_DT")
    private Date modDt;
}