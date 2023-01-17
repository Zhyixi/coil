package com.walsin.coil.service.pps.impl;

import com.walsin.coil.domain.entity.pps.Ppsinptb01;
import com.walsin.coil.domain.mybatis.base.BaseServiceImp;
import com.walsin.coil.domain.mybatis.mapper.pps.Ppsinptb01Mapper;
import com.walsin.coil.service.pps.Ppsinptb01Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jane
 * @date 2022/12/8 下午 03:51
 */
@Primary
@Service
public class Ppsinptb01ServiceImp extends BaseServiceImp<Ppsinptb01> implements Ppsinptb01Service {
    @Autowired
    private Ppsinptb01Mapper ppsinptb01Mapper ;
    @Override
    public List<Ppsinptb01> getAllFromXml() {
        return ppsinptb01Mapper.getAllFromXml();
    }

    @Override
    public List<Ppsinptb01> getAllFromSelect() {
        return ppsinptb01Mapper.getAllFromSelect();
    }
}
