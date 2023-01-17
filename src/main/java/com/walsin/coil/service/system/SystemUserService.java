package com.walsin.coil.service.system;

import com.walsin.coil.domain.entity.system.SystemUser;
import com.walsin.coil.domain.mybatis.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * @author Jane
 * @date 2022/12/8 上午 09:16
 */
public interface SystemUserService extends BaseService<SystemUser> {
    List<Map<String, Object>> getAllUser() ;

    SystemUser getUserSaltByUserName(String userName);

    SystemUser finUserByName(String userName) ;

}
