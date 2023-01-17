package com.walsin.coil.service.orp;

import com.walsin.coil.domain.entity.orp.ORPV101;
import com.walsin.coil.domain.entity.orp.ORPV999;
import com.walsin.coil.domain.mybatis.base.BaseService;

import java.util.List;

/**
 * @author ur10369
 * @date 2022/12/26 下午 2:35
 */
public interface ORPV101Service extends BaseService<ORPV101> {

    List<ORPV101> getCustList();

    List<ORPV999> getQuery();
}
