package com.walsin.coil.domain.mybatis.mapper.orp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.walsin.coil.domain.entity.orp.ORPE033;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ur09024
 * @date 2022/12/15 下午 3:30
 */
@Mapper
public interface ORPM033 extends BaseMapper<ORPE033> {
    List<ORPE033> getAllFromXml();

    @Delete("delete from tborpm033 where PLANT_CODE='TC'")
    boolean deleteall();

}
