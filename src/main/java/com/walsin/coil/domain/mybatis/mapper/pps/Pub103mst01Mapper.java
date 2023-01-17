package com.walsin.coil.domain.mybatis.mapper.pps;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.walsin.coil.domain.entity.pps.Pub103mst01;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wen
 * @date 2023/1/5 上午 8:45
 */
@Mapper
public interface Pub103mst01Mapper extends BaseMapper<Pub103mst01> {
    List<Pub103mst01> getAllFromXml();

    @Select("select * from pub103mst01")
    List<Pub103mst01> getAllFromSelect();



}
