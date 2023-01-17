package com.walsin.coil.service.oip;

import com.walsin.coil.domain.entity.oip.OIPE029;
import com.walsin.coil.domain.mybatis.base.BaseService;

import java.util.List;

/**
 * @author ur09024
 * @date 2022/12/15 下午 3:30
 */
public interface OIPS029 extends BaseService<OIPE029> {
    List<OIPE029> getAllFromXml();

    boolean deleteall();

}
