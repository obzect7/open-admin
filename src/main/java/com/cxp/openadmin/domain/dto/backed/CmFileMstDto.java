package com.cxp.openadmin.domain.dto.backed;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Getter
@Setter
public class CmFileMstDto {

    private BigDecimal file_seq;
    private String file_grp_seq;
    private String file_no;
    private String file_nm;
    private String file_type;
    private BigDecimal file_size;
    private String file_path;
    private String file_desc;
    private int sort_order;
    private String orgin_file_nm;
    private String file_cont;
    private String save_type;
    private String refer_id;
    private String refer_type;
    private String thumbnail_file_path;
    private String thumbnail_file_nm;
    private String thumbnail_file_cont;
    private String thumbnail_save_type;
    private BigDecimal thumbnail_file_size;
    private String use_yn;
    private String reg_id;
    private Timestamp reg_dt;
    private String mod_id;
    private Timestamp mod_dt;
}
