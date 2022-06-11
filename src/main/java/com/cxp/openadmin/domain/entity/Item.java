package com.cxp.openadmin.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@ToString
@Table(name = "mst_item")
public class Item {
    /**
     * 기본 키
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 사업장
     */
    private String plant_cd;

    /**
     * 화주
     */
    private String owner_cd;

    /**
     * 품번
     */
    private String item_cd;

    /**
     * 품명
     */
    private String item_nm;

    /**
     * 규격
     */
    private String spec;

    /**
     * 단위
     */
    private String unit;

    /**
     * 구분1
     */
    private String class1;

    /**
     * 구분2
     */
    private String class2;

    /**
     * 구분3
     */
    private String class3;

    /**
     * 박스당수량
     */
    private String boxqty;

    /**
     * 팔렛당수량
     */
    private String pltqty;

    /**
     * 입고가
     */
    private String in_price1;

    /**
     * 출고가
     */
    private String out_price1;

    /**
     * 로트관리여부
     */
    private String lot_yn;

    /**
     * 선입선출여부
     */
    private String fifo_yn;

    /**
     * 적정재고
     */
    private String appro_inv;

    /**
     * 입고창고코드
     */
    private String in_wh_cd;

    /**
     * 입고로케이션
     */
    private String in_lc_cd;

    /**
     * 비고사항
     */
    private String remark;

    /**
     * 사용여부
     */
    private String use_yn;

    /**
     * 등록자
     */
    private String reg_id;

    /**
     * 등록일시
     */
    private Date reg_dt;

    /**
     * 수정자
     */
    private String mod_id;

    /**
     * 수정일시
     */
    private Date mod_dt;

}