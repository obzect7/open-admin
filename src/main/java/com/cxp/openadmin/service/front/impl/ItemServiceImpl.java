package com.cxp.openadmin.service.front.impl;

import com.cxp.openadmin.dao.ItemMapper;
import com.cxp.openadmin.domain.dto.front.ItemDto;
import com.cxp.openadmin.service.front.ItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private ItemMapper itemMapper;

    @Override
    public List<ItemDto> getItemList(Map<String, Object> map) {
        return itemMapper.getItemList(map);
    }

//    @Override
//    public int saveCustomer(List<CustomerDto> list) {
//        if(list.size()> 0){
//            for(CustomerDto dto : list){
//                if("I".equals(dto.getRowStatus())){
//                    customerMapper.insertCustomer(dto);
//                }else if("U".equals(dto.getRowStatus())){
//                    customerMapper.updateCustomer(dto);
//                }else if("D".equals(dto.getRowStatus())){
//                    customerMapper.deleteCustomer(dto);
//                }
//            }
//        }
////        customerMapper.getCustomerList(map);
//        return 0;
//    }

}
