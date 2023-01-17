package com.walsin.coil.service.tra.impl;

import com.walsin.coil.domain.entity.tra.TRAE001;
import com.walsin.coil.domain.mybatis.base.BaseServiceImp;
import com.walsin.coil.domain.mybatis.mapper.tra.TRAM001;
import com.walsin.coil.service.tra.TRAS001;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class TRAS001Imp extends BaseServiceImp<TRAE001> implements TRAS001 {

    @Autowired
    private TRAM001 TRAM001;

    @Override
    public List<TRAE001> getAllFromXml() {return TRAM001.getAllFromXml();}
}
