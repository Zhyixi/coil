package com.walsin.coil.domain.mybatis.mapper.oip;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.walsin.coil.domain.entity.oip.OIPE029;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ur09024
 * @date 2022/12/15 下午 3:30
 */
@Mapper
public interface OIPM029 extends BaseMapper<OIPE029> {
    List<OIPE029> getAllFromXml();

    @Delete("delete from tboipm029 where PLANT_CODE='TC'")
    boolean deleteall();

}
