package com.walsin.coil.service.orp.impl;

import com.walsin.coil.domain.entity.orp.ORPP029;
import com.walsin.coil.domain.mybatis.base.BaseServiceImp;
import com.walsin.coil.domain.mybatis.mapper.orp.ORPP029Mapper;
import com.walsin.coil.service.orp.ORPP029Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ur09024
 * @date 2022/12/15 下午 3:30
 */
@Primary
@Service
public class ORPP029ServiceImp extends BaseServiceImp<ORPP029> implements ORPP029Service {
    @Autowired
    private ORPP029Mapper ORPP029Mapper;
    @Override
    public List<ORPP029> getAllFromXml() {return ORPP029Mapper.getAllFromXml();}
    @Override
    public List<ORPP029> getAllFromSelect() {return ORPP029Mapper.getAllFromSelect();}

    public boolean deleteall() {return ORPP029Mapper.deleteall();}

}
