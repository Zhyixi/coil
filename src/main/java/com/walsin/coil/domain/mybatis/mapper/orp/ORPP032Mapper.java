package com.walsin.coil.domain.mybatis.mapper.orp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.walsin.coil.domain.entity.orp.ORPP032;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ur09024
 * @date 2022/12/15 下午 3:30
 */
@Mapper
public interface ORPP032Mapper extends BaseMapper<ORPP032> {
    List<ORPP032> getAllFromXml();
    @Select("select * from tborpm032 where PLANT_CODE='TC'")
    List<ORPP032> getAllFromSelect();

    @Delete("delete from tborpm032 where PLANT_CODE='TC'")
    boolean deleteall();


}
