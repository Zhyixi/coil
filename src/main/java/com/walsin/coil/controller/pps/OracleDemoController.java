package com.walsin.coil.controller.pps;

import com.walsin.coil.common.result.Result;
import com.walsin.coil.common.utils.ResultUtil;
import com.walsin.coil.domain.entity.pps.Ppsinptb01;
import com.walsin.coil.domain.entity.pps.Pub103mst01;
import com.walsin.coil.domain.mybatis.pps.Ppsinpara;
import com.walsin.coil.service.pps.Ppsinptb01Service;
import com.walsin.coil.service.pps.Pub103mst01Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wen
 * @date 2023/1/5 上午 8:45
 */
@Slf4j
@RestController
@Api(tags = "oracle 使用範例")
@RequestMapping("/oracleDemo")
public class OracleDemoController {
    @Autowired
    private Pub103mst01Service pub103mst01Service ;

    @ApiOperation("oracle获取数据通过xml")
    @RequestMapping(value = "/getMESAllFromXml", method = RequestMethod.GET)
    public Result<Object> getMESAllFromXml() {
        List<Pub103mst01> data = pub103mst01Service.getMESAllFromXml();
        return new ResultUtil<>().setData(data);
    }

    @ApiOperation("oracle获取d数据通过注解")
    @RequestMapping(value = "/getMESAllFromSelect", method = RequestMethod.GET)
    public Result<Object> getMESAllFromSelect() {
        List<Pub103mst01> data = pub103mst01Service.getMESAllFromSelect();
        return new ResultUtil<>().setData(data);
    }




}
