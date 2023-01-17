package com.walsin.coil.service.orp.impl;

import com.walsin.coil.domain.entity.orp.ORPP030;
import com.walsin.coil.domain.mybatis.base.BaseServiceImp;
import com.walsin.coil.domain.mybatis.mapper.orp.ORPP030Mapper;
import com.walsin.coil.service.orp.ORPP030Service;
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
public class ORPP030ServiceImp extends BaseServiceImp<ORPP030> implements ORPP030Service {
    @Autowired
    private ORPP030Mapper ORPP030Mapper;
    @Override
    public List<ORPP030> getAllFromXml() {
        return ORPP030Mapper.getAllFromXml();
    }
    @Override
    public List<ORPP030> getAllFromSelect() {
        return ORPP030Mapper.getAllFromSelect();
    }

    public boolean deleteall() {return ORPP030Mapper.deleteall();}

}
