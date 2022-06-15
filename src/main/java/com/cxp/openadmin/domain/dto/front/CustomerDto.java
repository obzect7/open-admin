package com.cxp.openadmin.domain.dto.front;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CustomerDto {

    private String row_status;  //row 상태
    private String plant_cd;
    private String owner_cd;
    private String cust_cd;
    private String cust_nm;
    private String cust_desc;
    private String ceo;
    private String business_no;
    private String corp_no;
    private String tel;
    private String post_no;
    private String addr1;
    private String addr2;
    private String fax;
    private String email;
    private String business_type;
    private String business_item;
    private String first_day;
    private String remark;
    private String use_yn;
    private String reg_id;
    private Timestamp reg_dt;
    private String mod_id;
    private Timestamp mod_dt;
}
