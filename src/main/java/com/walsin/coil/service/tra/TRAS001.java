package com.walsin.coil.service.tra;

import com.walsin.coil.domain.entity.tra.TRAE001;
import com.walsin.coil.domain.mybatis.base.BaseService;

import java.util.List;

public interface TRAS001 extends BaseService<TRAE001> {
    List<TRAE001> getAllFromXml();
}
