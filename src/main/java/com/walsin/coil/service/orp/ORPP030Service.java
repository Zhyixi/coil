package com.walsin.coil.service.orp;

import com.walsin.coil.domain.entity.orp.ORPP030;
import com.walsin.coil.domain.mybatis.base.BaseService;

import java.util.List;

/**
 * @author ur09024
 * @date 2022/12/15 下午 3:30
 */
public interface ORPP030Service extends BaseService<ORPP030> {
    List<ORPP030> getAllFromXml();
    List<ORPP030> getAllFromSelect();

    boolean deleteall();

}
