package com.walsin.coil.controller.orp;


import com.walsin.coil.common.result.Result;
import com.walsin.coil.common.utils.ResultUtil;
import com.walsin.coil.domain.entity.orp.ORPV101;
import com.walsin.coil.domain.entity.orp.ORPV999;
import com.walsin.coil.domain.model.UserPara;
import com.walsin.coil.domain.model.orp.ORPV101para;
import com.walsin.coil.service.orp.ORPV101Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ur10369
 * @date 2022/12/26 下午 2:35
 */
@Slf4j
@RestController
@Api(tags = "需求執行/特殊詢單")
@RequestMapping("/ORPV101")
public class ORPV101Controller {

    @Autowired
    private ORPV101Service ORPV101Service;

    @ApiOperation("取得客戶名稱 getCustList")
    @RequestMapping(value = "/getCustList", method = RequestMethod.GET)
    public Result<Object> getCustList() {
        List<ORPV101> custAbbreviations = ORPV101Service.getCustList();
        log.info("test : {}",custAbbreviations);
        return new ResultUtil<>().setData(custAbbreviations);
    }


    @ApiOperation("取得送出查詢資料 getQuery")
    @RequestMapping(value = "/getQuery", method = RequestMethod.POST)
    public Result<Object> getQuery(@RequestBody ORPV101para pra) {
        Map<String, Object> map = new HashMap<>();
        List<ORPV999> data = ORPV101Service.getQuery();

        System.out.println(data);
        return new ResultUtil<>().setData(data);
    }



}
