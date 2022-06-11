package com.cxp.openadmin.service.front;

import com.cxp.openadmin.domain.dto.front.ItemDto;

import java.util.List;
import java.util.Map;

public interface ItemService {

    List<ItemDto> getItemList(Map<String, Object> map);

    //int saveCustomer(List<CustomerDto> list);
}
