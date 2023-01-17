package com.walsin.coil.service.orp;

import com.walsin.coil.domain.entity.orp.ORPP032;
import com.walsin.coil.domain.mybatis.base.BaseService;

import java.util.List;

/**
 * @author ur09024
 * @date 2022/12/15 下午 3:30
 */
public interface ORPP032Service extends BaseService<ORPP032> {
    List<ORPP032> getAllFromXml();
    List<ORPP032> getAllFromSelect();
    boolean deleteall();
}
