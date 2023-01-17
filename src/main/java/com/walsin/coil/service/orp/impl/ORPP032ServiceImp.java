package com.walsin.coil.service.orp.impl;

import com.walsin.coil.domain.entity.orp.ORPP032;
import com.walsin.coil.domain.mybatis.base.BaseServiceImp;
import com.walsin.coil.domain.mybatis.mapper.orp.ORPP032Mapper;
import com.walsin.coil.service.orp.ORPP032Service;
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
public class ORPP032ServiceImp extends BaseServiceImp<ORPP032> implements ORPP032Service {
    @Autowired
    private ORPP032Mapper ORPP032Mapper;
    @Override
    public List<ORPP032> getAllFromXml() {
        return ORPP032Mapper.getAllFromXml();
    }
    @Override
    public List<ORPP032> getAllFromSelect() {
        return ORPP032Mapper.getAllFromSelect();
    }

    public boolean deleteall() {return ORPP032Mapper.deleteall();}
}
