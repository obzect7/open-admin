package com.cxp.openadmin.domain.dto.front;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class FileDto {


    private String row_status;  //row
    private String file_seq;
    private String file_grp_seq;
    private String file_no;
    private String file_nm;
    private String file_type;
    double file_size;
    private String file_path;
    private String file_desc;
    double sort_order;
    private String orgin_file_nm;
    //file_cont
    private String save_type;
    private String refer_id;
    private String refer_type;
    private String thumbnail_file_path;
    private String thumbnail_file_nm;
    //thumbnail_file_cont
    private String thumbnail_save_type;
    double thumbnail_file_size;
    private String use_yn;
    private String reg_id;
    private Timestamp reg_dt;
    private String mod_id;
    private Timestamp mod_dt;
}
