package com.walsin.coil.service.orp;

import com.walsin.coil.domain.entity.orp.ORPE033;
import com.walsin.coil.domain.mybatis.base.BaseService;

import java.util.List;

/**
 * @author ur09024
 * @date 2022/12/15 下午 3:30
 */
public interface ORPS033 extends BaseService<ORPE033> {
    List<ORPE033> getAllFromXml();

    boolean deleteall();

}
