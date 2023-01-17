package com.walsin.coil.service.pps;

import com.walsin.coil.domain.entity.pps.Ppsinptb01;
import com.walsin.coil.domain.mybatis.base.BaseService;

import java.util.List;

/**
 * @author Jane
 * @date 2022/12/8 下午 03:50
 */
public interface Ppsinptb01Service extends BaseService<Ppsinptb01> {
    List<Ppsinptb01> getAllFromXml();
    List<Ppsinptb01> getAllFromSelect();
}
