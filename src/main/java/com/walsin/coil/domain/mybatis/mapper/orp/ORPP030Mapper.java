package com.walsin.coil.domain.mybatis.mapper.orp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.walsin.coil.domain.entity.orp.ORPP030;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ur09024
 * @date 2022/12/15 下午 3:30
 */
@Mapper
public interface ORPP030Mapper extends BaseMapper<ORPP030> {
    List<ORPP030> getAllFromXml();
    @Select("select * from tborpm030 where PLANT_CODE='TC'")
    List<ORPP030> getAllFromSelect();

    @Delete("delete from tborpm030 where PLANT_CODE='TC'")
    boolean deleteall();

}
