package com.walsin.coil.service.pps;

import com.walsin.coil.domain.entity.pps.Ppsinptb01;
import com.walsin.coil.domain.entity.pps.Pub103mst01;
import com.walsin.coil.domain.mybatis.base.BaseService;

import java.util.List;

/**
 * @author wen
 * @date 2023/1/5 上午 8:45
 */
public interface Pub103mst01Service extends BaseService<Pub103mst01> {
    List<Pub103mst01> getMESAllFromXml();
    List<Pub103mst01> getMESAllFromSelect();
}
