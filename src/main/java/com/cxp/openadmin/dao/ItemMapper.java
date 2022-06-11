package com.cxp.openadmin.dao;

import com.cxp.openadmin.domain.dto.front.ItemDto;
import com.cxp.openadmin.domain.entity.Item;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface ItemMapper extends Mapper<Item> {

    List<ItemDto> getItemList(@Param("paramsMap") Map<String, Object> map);

//    int insertCustomer(@Param("paramsMap") CustomerDto dto);
//    int updateCustomer(@Param("paramsMap") CustomerDto dto);
//    int deleteCustomer(@Param("paramsMap") CustomerDto dto);
}