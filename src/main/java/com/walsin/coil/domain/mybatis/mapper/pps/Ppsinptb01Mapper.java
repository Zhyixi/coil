package com.walsin.coil.domain.mybatis.mapper.pps;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.walsin.coil.domain.entity.pps.Ppsinptb01;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Jane
 * @date 2022/12/8 下午 03:48
 */
@Mapper
public interface Ppsinptb01Mapper extends BaseMapper<Ppsinptb01> {
    List<Ppsinptb01> getAllFromXml();
    @Select("select * from ppsinptb01")
    List<Ppsinptb01> getAllFromSelect();



}
