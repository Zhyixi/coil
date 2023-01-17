package com.walsin.coil.controller.pps;

import com.walsin.coil.common.result.Result;
import com.walsin.coil.common.utils.ResultUtil;
import com.walsin.coil.domain.entity.pps.Ppsinptb01;
import com.walsin.coil.domain.mybatis.pps.Ppsinpara;
import com.walsin.coil.service.pps.Ppsinptb01Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jane
 * @date 2022/12/8 下午 04:22
 */
@Slf4j
@RestController
@Api(tags = "pps请求範例")
@RequestMapping("/requestPpsDemo")
public class PpsDemoController {
    @Autowired
    private Ppsinptb01Service ppsinptb01Service ;
    @ApiOperation("看这里------获取dev数据通过封装通用service")
    @RequestMapping(value = "/getPpsData", method = RequestMethod.GET)
    public Result<Object> getPpsData() {
        List<Ppsinptb01> users = ppsinptb01Service.findAll();
        log.info("test : {}",users);
        return new ResultUtil<>().setData(users);
    }

    @ApiOperation("看这里------获取dev数据通过xml")
    @RequestMapping(value = "/getAllFromXml", method = RequestMethod.GET)
    public Result<Object> getAllFromXml() {
        List<Ppsinptb01> users = ppsinptb01Service.getAllFromXml();
        return new ResultUtil<>().setData(users);
    }

    @ApiOperation("看这里------获取dev数据通过注解")
    @RequestMapping(value = "/getAllFromSelect", method = RequestMethod.GET)
    public Result<Object> getAllFromSelect() {
        List<Ppsinptb01> users = ppsinptb01Service.getAllFromSelect();
        return new ResultUtil<>().setData(users);
    }


    @ApiOperation("看这里------刪除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "輸入id",required = true)
    })
    @RequestMapping(value = "/deleteData", method = RequestMethod.POST)
    public Result<Object> deleteData(@RequestParam("id") Integer id) {
        int flag = ppsinptb01Service.deleteById(id);
        log.info("delete result : {}"+flag);
        return new ResultUtil<>().setData(flag);
    }




    @ApiOperation("看这里------修改")
    @RequestMapping(value = "/updateData", method = RequestMethod.POST)
    public Result<Object> updateData(@RequestBody Ppsinpara ppsinpara) {
        Ppsinptb01 ppsinptb01 = new Ppsinptb01() ;
        ppsinptb01 = ppsinptb01Service.selectById(ppsinpara.getId()) ;
        ppsinptb01.setGradeNo(ppsinpara.getGradeNo());
        ppsinptb01.setGradeGroup(ppsinpara.getGradeGroup());
        int flag = ppsinptb01Service.updateData(ppsinptb01);
        log.info("delete result : {}"+flag);
        return new ResultUtil<>().setData(flag);
    }

    @ApiOperation("看这里------新增")
    @RequestMapping(value = "/saveData", method = RequestMethod.POST)
    public Result<Object> saveData(@RequestBody Ppsinpara ppsinpara) {
        Ppsinptb01 ppsinptb01 = new Ppsinptb01() ;
        ppsinptb01.setGradeNo(ppsinpara.getGradeNo());
        ppsinptb01.setGradeGroup(ppsinpara.getGradeGroup());
        int flag = ppsinptb01Service.insert(ppsinptb01);
        log.info("delete result : {}"+flag);
        return new ResultUtil<>().setData(flag);
    }




}
