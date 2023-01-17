package com.walsin.coil.domain.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.walsin.coil.domain.entity.system.SystemUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Jane
 * @date 2022/12/8 上午 09:15
 */
@Mapper
public interface SystemUserMapper extends BaseMapper<SystemUser> {
    // @Select("select * from system_users")
    List<SystemUser> getAll() ;
    @Select("select * from system_users where id = #{id}")
    SystemUser getUserById(Integer id);
    @Select(" select su.salt  from system_users su  where su.user_name = '#{userName}' ")
    SystemUser getUserSaltByUserName(String userName) ;
    @Select("select *  from system_users su  where su.user_name = #{userName}")
    SystemUser finUserByName(String userName) ;
}
