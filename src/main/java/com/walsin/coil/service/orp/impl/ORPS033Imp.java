package com.walsin.coil.service.orp.impl;

import com.walsin.coil.domain.entity.orp.ORPE033;
import com.walsin.coil.domain.mybatis.base.BaseServiceImp;
import com.walsin.coil.domain.mybatis.mapper.orp.ORPM033;
import com.walsin.coil.service.orp.ORPS033;
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
public class ORPS033Imp extends BaseServiceImp<ORPE033> implements ORPS033 {
    @Autowired
    private ORPM033 ORPM033;
    @Override
    public List<ORPE033> getAllFromXml() {return ORPM033.getAllFromXml();}

    public boolean deleteall() {return ORPM033.deleteall();}

}
