package com.walsin.coil.service.system.impl;

import com.walsin.coil.domain.entity.system.SystemUser;
import com.walsin.coil.domain.mybatis.base.BaseServiceImp;
import com.walsin.coil.domain.mybatis.mapper.SystemUserMapper;
import com.walsin.coil.service.system.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Jane
 * @date 2022/12/8 上午 09:19
 */
@Service
public class SystemUserServiceImp extends BaseServiceImp<SystemUser> implements SystemUserService {
    @Autowired
    SystemUserMapper userMapper ;

    @Override
    public List<Map<String, Object>> getAllUser() {
        return userMapper.selectMaps(null);
    }

    @Override
    public SystemUser getUserSaltByUserName(String userName) {
        return userMapper.getUserSaltByUserName(userName);
    }

    @Override
    public SystemUser finUserByName(String userName) {
        SystemUser user = userMapper.finUserByName(userName) ;
        return user;
    }
}
