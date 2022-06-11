package com.cxp.openadmin.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@ToString
@Table(name = "mst_customer")
public class Customer {
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
     * 거래처코드
     */
    private String cust_cd;

    /**
     * 거래처명
     */
    private String cust_nm;

    /**
     * 거래처약어
     */
    private String cust_desc;

    /**
     * 대표자
     */
    private String ceo;

    /**
     * 사업자번호
     */
    private String business_no;

    /**
     * 법인번호
     */
    private String corp_no;

    /**
     * 전화번호
     */
    private String tel;

    /**
     * 우편번호
     */
    private String post_no;

    /**
     * 주소1
     */
    private String addr1;

    /**
     * 주소2
     */
    private String addr2;

    /**
     * 팩스번호
     */
    private String fax;

    /**
     * 이메일
     */
    private String email;

    /**
     * 업태
     */
    private String condition;

    /**
     * 종목
     */
    private String categorie;

    /**
     * 최초거래일
     */
    private String first_day;

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