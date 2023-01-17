package com.walsin.coil.service.orp;

import com.walsin.coil.domain.entity.orp.ORPP029;
import com.walsin.coil.domain.mybatis.base.BaseService;

import java.util.List;

/**
 * @author ur09024
 * @date 2022/12/15 下午 3:30
 */
public interface ORPP029Service extends BaseService<ORPP029> {
    List<ORPP029> getAllFromXml();
    List<ORPP029> getAllFromSelect();

    boolean deleteall();

}
