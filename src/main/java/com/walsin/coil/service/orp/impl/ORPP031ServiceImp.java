package com.walsin.coil.service.orp.impl;

import com.walsin.coil.domain.entity.orp.ORPP031;
import com.walsin.coil.domain.mybatis.base.BaseServiceImp;
import com.walsin.coil.domain.mybatis.mapper.orp.ORPP031Mapper;
import com.walsin.coil.service.orp.ORPP031Service;
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
public class ORPP031ServiceImp extends BaseServiceImp<ORPP031> implements ORPP031Service {
    @Autowired
    private ORPP031Mapper ORPP031Mapper;
    @Override
    public List<ORPP031> getAllFromXml() {
        return ORPP031Mapper.getAllFromXml();
    }
    @Override
    public List<ORPP031> getAllFromSelect() {
        return ORPP031Mapper.getAllFromSelect();
    }

    public boolean deleteall() {return ORPP031Mapper.deleteall();}

}
