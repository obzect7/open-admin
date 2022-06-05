package com.cxp.openadmin.service.backed.impl;

import com.cxp.openadmin.dao.*;
import com.cxp.openadmin.domain.dto.backed.*;
import com.cxp.openadmin.domain.entity.*;
import com.cxp.openadmin.domain.vo.backed.PermissionVo;
import com.cxp.openadmin.domain.vo.backed.RoleMenuAndMenuPermissionVo;
import com.cxp.openadmin.domain.vo.backed.SysMenuVo;
import com.cxp.openadmin.domain.vo.backed.SysRoleMenuPermissionVo;
import com.cxp.openadmin.exception.http.OperatorException;
import com.cxp.openadmin.exception.http.ParamException;
import com.cxp.openadmin.exception.http.VerifyException;
import com.cxp.openadmin.service.backed.CommonCodeService;
import com.cxp.openadmin.util.Md5Util;
import com.cxp.openadmin.util.PagedGridResult;
import com.cxp.openadmin.util.TreeParser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommonCodeServiceImpl implements CommonCodeService {

    @Resource
    private CmCodeGrpMapper cmCodeGrpMapper;

    @Resource
    private CmCodeMapper cmCodeMapper;

    @Override
    public List<SaveCmCodeGrpDto> getCmCodeGrpList(Map<String, Object> map) {
        return cmCodeGrpMapper.getCmCodeGrpList(map);
    }


    @Override
    public void saveCmCodeGrp(SaveCmCodeGrpDto saveCmCodeGrpDto) {

//        cmCodeGrpMapper.insertSelective(saveCmCodeGrpDto);

    }

}
