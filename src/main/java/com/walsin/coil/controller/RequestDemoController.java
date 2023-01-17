package com.walsin.coil.controller;

import cn.hutool.json.JSONObject;

import com.walsin.coil.common.result.Result;
import com.walsin.coil.common.utils.ResultUtil;
import com.walsin.coil.domain.entity.pps.Ppsinptb01;
import com.walsin.coil.domain.entity.system.SystemUser;
import com.walsin.coil.domain.model.UserPara;
import com.walsin.coil.service.pps.Ppsinptb01Service;
import com.walsin.coil.service.system.SystemUserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jane
 * @date 2022/12/7 下午 04:33
 */
@Slf4j
@RestController
@Api(tags = "请求範例")
@RequestMapping("/requestDemo")
public class RequestDemoController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SystemUserService systemUserService ;
    @Autowired
    private Ppsinptb01Service ppsinptb01Service ;
    //一般请求 get post put delete 支援
    @ApiOperation("PathVariable 注解使用")
    @RequestMapping(value = "/path/{name}/{age}", method = RequestMethod.GET)
    public Result<Object> getDataByPath(@ApiParam("名字") @PathVariable("name") String name, @ApiParam("年龄") @PathVariable("age") Integer age) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("age", age);
        return new ResultUtil<>().setData(map);
    }
    @ApiOperation("測試使用")
    @GetMapping("/param/get")
    public Result<Object> getDataByParamGet(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("age", age);
        return new ResultUtil<>().setData(map);
    }

    @PostMapping("/param/post")
    public Result<Object> getDataByParamPost(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("age", age);
        return new ResultUtil<>().setData(map);
    }

    @PostMapping("/body/post")
    public Result<Object> getDataByBody(@RequestBody String jsonObj) {
        JSONObject json = new JSONObject(jsonObj);
        return new ResultUtil<>().setData(json);
    }
    @ApiOperation("測試使用")
    @RequestMapping(value = "/getUserByPara",method = RequestMethod.POST)
    public Result<Object> getUserByPara(@RequestBody UserPara userPara) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", userPara.getUserName());
        map.put("age", userPara.getAge());
        return new ResultUtil<>().setData(map);
    }
    @ApiOperation("從數據中獲取所有用戶")
    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public Result<Object> getUsers() {
        List<SystemUser> users = systemUserService.findAll();
        return new ResultUtil<>().setData(users);
    }

    @ApiOperation("從數據中獲取某個用戶")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "輸入用戶名",required = true,paramType = "query"),
    })
    @RequestMapping(value = "/getUsersByUserName", method = RequestMethod.GET)
    public Result<Object> getUsersByUserName(@RequestParam("name") String name) {
        SystemUser user = systemUserService.finUserByName(name);
        if(user == null) {
            return new ResultUtil<>().setErrorMsg("查詢不到該用戶");
        }
        return new ResultUtil<>().setData(user);
    }

    @ApiOperation("看这里------获取dev数据通过封装通用service")
    @RequestMapping(value = "/getPpsData", method = RequestMethod.GET)
    public Result<Object> getPpsData() {
        List<Ppsinptb01> users = ppsinptb01Service.findAll();
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

    @GetMapping("/home")
    public String home() {
        return "forward:/index.html";
    }
}
