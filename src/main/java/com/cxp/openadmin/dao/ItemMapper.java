package com.cxp.openadmin.dao;

import com.cxp.openadmin.domain.dto.front.ItemDto;
import com.cxp.openadmin.domain.entity.Item;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface ItemMapper extends Mapper<Item> {

    List<ItemDto> getItemList(@Param("paramsMap") Map<String, Object> map);

    int insertItem(@Param("paramsMap") ItemDto dto);

    int updateItem(@Param("paramsMap") ItemDto dto);

    int updateFileGrpSeq(@Param("paramsMap") ItemDto dto);

    int deleteItem(@Param("paramsMap") ItemDto dto);

}