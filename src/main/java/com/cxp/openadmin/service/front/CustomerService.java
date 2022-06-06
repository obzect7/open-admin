package com.cxp.openadmin.service.front;

import com.cxp.openadmin.domain.dto.front.CustomerDto;

import java.util.List;
import java.util.Map;

public interface CustomerService {

    List<CustomerDto> getCustomerList(Map<String, Object> map);
}
