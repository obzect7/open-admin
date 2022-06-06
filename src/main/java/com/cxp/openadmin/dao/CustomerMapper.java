package com.cxp.openadmin.dao;

import com.cxp.openadmin.domain.entity.CmCodeGrp;
import com.cxp.openadmin.domain.dto.front.CustomerDto;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface CustomerMapper extends Mapper<CmCodeGrp> {

    List<CustomerDto> getCustomerList(@Param("paramsMap") Map<String, Object> map);
}