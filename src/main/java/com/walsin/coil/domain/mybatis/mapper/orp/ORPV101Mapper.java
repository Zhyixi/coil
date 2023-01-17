package com.walsin.coil.domain.mybatis.mapper.orp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.walsin.coil.domain.entity.orp.ORPV101;
import com.walsin.coil.domain.entity.orp.ORPV999;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ur10369
 * @date 2022/12/26 下午 2:35
 */
@Mapper
public interface ORPV101Mapper extends BaseMapper<ORPV101> {

    @Select("select '生計預留' as CUST_ABBREVIATIONS union all select CUST_ABBREVIATIONS from ppsinptb15 group by CUST_ABBREVIATIONS")
    List<ORPV101> getCustList();


    @Select("select * from tborpm999")
    List<ORPV999> getQuery();


}
