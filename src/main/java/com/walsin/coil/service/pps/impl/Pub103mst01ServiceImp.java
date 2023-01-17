package com.walsin.coil.service.pps.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.walsin.coil.domain.entity.pps.Pub103mst01;
import com.walsin.coil.domain.mybatis.base.BaseServiceImp;
import com.walsin.coil.domain.mybatis.mapper.pps.Pub103mst01Mapper;
import com.walsin.coil.service.pps.Pub103mst01Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wen
 * @date 2023/1/5 上午 8:45
 */
@Primary
@Service
@DS("oracle")
public class Pub103mst01ServiceImp extends BaseServiceImp<Pub103mst01> implements Pub103mst01Service {
    @Autowired
    private Pub103mst01Mapper Pub103mst01Mapper ;
    @Override
    public List<Pub103mst01> getMESAllFromXml() {
        return Pub103mst01Mapper.getAllFromXml();
    }

    @Override
    public List<Pub103mst01> getMESAllFromSelect() {
        return Pub103mst01Mapper.getAllFromSelect();
    }
}
