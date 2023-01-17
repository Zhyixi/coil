package com.walsin.coil.service.orp;

import com.walsin.coil.domain.entity.orp.ORPP031;
import com.walsin.coil.domain.mybatis.base.BaseService;

import java.util.List;

/**
 * @author ur09024
 * @date 2022/12/15 下午 3:30
 */
public interface ORPP031Service extends BaseService<ORPP031> {
    List<ORPP031> getAllFromXml();
    List<ORPP031> getAllFromSelect();

    boolean deleteall();

}
