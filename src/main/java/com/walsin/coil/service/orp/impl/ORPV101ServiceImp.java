package com.walsin.coil.service.orp.impl;

import com.walsin.coil.domain.entity.orp.ORPV101;
import com.walsin.coil.domain.entity.orp.ORPV999;
import com.walsin.coil.domain.mybatis.base.BaseServiceImp;
import com.walsin.coil.domain.mybatis.mapper.orp.ORPV101Mapper;
import com.walsin.coil.service.orp.ORPV101Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ur10369
 * @date 2022/12/26 下午 2:35
 */
@Primary
@Service
public class ORPV101ServiceImp extends BaseServiceImp<ORPV101> implements ORPV101Service {
    @Autowired
    private ORPV101Mapper ORPV101Mapper;

    @Override
    public List<ORPV101> getCustList() {
        return ORPV101Mapper.getCustList();
    }

    @Override
    public List<ORPV999> getQuery() {
        return ORPV101Mapper.getQuery();
    }

}
