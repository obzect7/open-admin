package com.cxp.openadmin.service.front.impl;

import com.cxp.openadmin.dao.CustomerMapper;
import com.cxp.openadmin.domain.dto.front.CustomerDto;
import com.cxp.openadmin.service.front.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerMapper customerMapper;

    @Override
    public List<CustomerDto> getCustomerList(Map<String, Object> map) {
        return customerMapper.getCustomerList(map);
    }

}
