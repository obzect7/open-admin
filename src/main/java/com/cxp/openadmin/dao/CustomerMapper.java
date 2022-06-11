package com.cxp.openadmin.dao;

import com.cxp.openadmin.domain.dto.front.CustomerDto;
import com.cxp.openadmin.domain.entity.Customer;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface CustomerMapper extends Mapper<Customer> {

    List<CustomerDto> getCustomerList(@Param("paramsMap") Map<String, Object> map);

    int insertCustomer(@Param("paramsMap") CustomerDto dto);
    int updateCustomer(@Param("paramsMap") CustomerDto dto);
    int deleteCustomer(@Param("paramsMap") CustomerDto dto);
}