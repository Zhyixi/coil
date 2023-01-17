package com.walsin.coil.domain.mybatis.mapper.orp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.walsin.coil.domain.entity.orp.ORPP031;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ur09024
 * @date 2022/12/15 下午 3:30
 */
@Mapper
public interface ORPP031Mapper extends BaseMapper<ORPP031> {
    List<ORPP031> getAllFromXml();
    @Select("select * from tborpm031 where PLANT_CODE='TC'")
    List<ORPP031> getAllFromSelect();

    @Delete("delete from tborpm031 where PLANT_CODE='TC'")
    boolean deleteall();

}
