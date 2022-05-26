package com.cxp.openadmin.domain.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import java.util.Date;

@Data
public class UserExcelProperty extends BaseRowModel {

    @ExcelProperty(value = "Id", index = 0)
    private Integer id;

    @ExcelProperty(value = "닉네임", index = 1)
    private String nickname;

    @ExcelProperty(value = "전화 번호", index = 2)
    private String mobile;

    @ExcelProperty(value = "성별", index = 3)
    private String gender;

    @ExcelProperty(value = "사용자 상태", index = 4)
    private String status;

    @ExcelProperty(value = "생성 시간", index = 5)
    private Date createdTime;

}
