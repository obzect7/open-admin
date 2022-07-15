package com.cxp.openadmin.domain.dto.front;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ItemDto {

    private String row_status;  //row 상태
    private String plant_cd;
    private String owner_cd;
    private String item_cd;
    private String item_nm;
    private String spec;
    private String unit;
    private String class1;
    private String class2;
    private String class3;
    private String boxqty;
    private String pltqty;
    private String in_price1;
    private String out_price1;
    private String lot_yn;
    private String fifo_yn;
    private String appro_inv;
    private String in_wh_cd;
    private String in_lc_cd;
    private String remark;
    private String use_yn;
    private String reg_id;
    private Timestamp reg_dt;
    private String mod_id;
    private Timestamp mod_dt;
    private String file_grp_seq;
}
