package com.walsin.coil.service.oip.impl;

import com.walsin.coil.domain.entity.oip.OIPE029;
import com.walsin.coil.domain.mybatis.base.BaseServiceImp;
import com.walsin.coil.domain.mybatis.mapper.oip.OIPM029;
import com.walsin.coil.service.oip.OIPS029;
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
public class OIPS029Imp extends BaseServiceImp<OIPE029> implements OIPS029 {
    @Autowired
    private OIPM029 OIPM029;
    @Override
    public List<OIPE029> getAllFromXml() {return OIPM029.getAllFromXml();}

    public boolean deleteall() {return OIPM029.deleteall();}

}
