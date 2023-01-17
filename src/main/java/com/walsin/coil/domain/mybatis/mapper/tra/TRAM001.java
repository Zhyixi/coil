package com.walsin.coil.domain.mybatis.mapper.tra;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.walsin.coil.domain.entity.tra.TRAE001;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TRAM001 extends BaseMapper<TRAE001> {
    List<TRAE001> getAllFromXml();
}
