package com.cxp.openadmin.dao;

import com.cxp.openadmin.domain.dto.front.FileDto;
import com.cxp.openadmin.domain.entity.Item;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface FileMapper extends Mapper<Item> {

    //List<ItemDto> getItemList(@Param("paramsMap") Map<String, Object> map);

    int insertfile(@Param("paramsMap") FileDto dto);

    //int updateItem(@Param("paramsMap") ItemDto dto);

    //int updateFileGrpSeq(@Param("paramsMap") ItemDto dto);

    //int deleteItem(@Param("paramsMap") ItemDto dto);

}