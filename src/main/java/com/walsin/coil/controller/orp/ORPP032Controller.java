package com.walsin.coil.controller.orp;

import com.walsin.coil.common.result.Result;
import com.walsin.coil.common.utils.ResultUtil;
import com.walsin.coil.domain.entity.orp.ORPP032;
import com.walsin.coil.domain.model.orp.ORPP032para;
import com.walsin.coil.service.orp.ORPP032Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ur09024
 * @date 2022/12/15 下午 3:30
 */
@Slf4j
@RestController
@Api(tags = "訂單厚度原料對照表")
@RequestMapping("/requestTborpm032")
public class ORPP032Controller {
    @Autowired
    private ORPP032Service tborpm032Service;
    @ApiOperation("取得資料byService")
    @RequestMapping(value = "/getOrpData", method = RequestMethod.GET)
    public Result<Object> getOrpData() {
        List<ORPP032> users = tborpm032Service.findAll();
        log.info("test : {}",users);
        return new ResultUtil<>().setData(users);
    }

    @ApiOperation("取得資料byXml")
    @RequestMapping(value = "/getAllFromXml", method = RequestMethod.GET)
    public Result<Object> getAllFromXml() {
        List<ORPP032> users = tborpm032Service.getAllFromXml();
        return new ResultUtil<>().setData(users);
    }

    @ApiOperation("取得資料by註解")
    @RequestMapping(value = "/getAllFromSelect", method = RequestMethod.GET)
    public Result<Object> getAllFromSelect() {
        List<ORPP032> users = tborpm032Service.getAllFromSelect();
        return new ResultUtil<>().setData(users);
    }


    @ApiOperation("刪除byId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "輸入id",required = true)
    })
    @RequestMapping(value = "/deleteData", method = RequestMethod.POST)
    public Result<Object> deleteData(@RequestParam("id") Integer id) {
        int flag = tborpm032Service.deleteById(id);
        log.info("delete result : " + flag);
        return new ResultUtil<>().setData(flag);
    }




    @ApiOperation("修改byId")
    @RequestMapping(value = "/updateData", method = RequestMethod.POST)
    public Result<Object> updateData(@RequestBody ORPP032para tborpm032Para) {
        ORPP032 tborpm032 = new ORPP032() ;
        tborpm032 = tborpm032Service.selectById(tborpm032Para.getId());
        tborpm032.setPlantCode("TC");
        tborpm032.setProductType(tborpm032Para.getProductType());
        tborpm032.setThicknessMin(tborpm032Para.getThicknessMin());
        tborpm032.setThicknessMax(tborpm032Para.getThicknessMax());
        tborpm032.setInputType(tborpm032Para.getInputType());
        tborpm032.setOptionSeq(tborpm032Para.getOptionSeq());
        tborpm032.setMaterialThicknessMin(tborpm032Para.getMaterialThicknessMin());
        tborpm032.setMaterialThicknessMax(tborpm032Para.getMaterialThicknessMax());
        tborpm032.setDateUpdate(LocalDateTime.now()); // 代入系統時間
        tborpm032.setUserUpdate(tborpm032Para.getUserUpdate());
        int flag = tborpm032Service.updateData(tborpm032);
        log.info("update result : " + flag);
        return new ResultUtil<>().setData(flag);
    }

    @ApiOperation("單筆新增")
    @RequestMapping(value = "/saveData", method = RequestMethod.POST)
    public Result<Object> saveData(@RequestBody ORPP032para tborpm032Para) {
        ORPP032 tborpm032 = new ORPP032();
        tborpm032.setPlantCode("TC");
        tborpm032.setProductType(tborpm032Para.getProductType());
        tborpm032.setThicknessMin(tborpm032Para.getThicknessMin());
        tborpm032.setThicknessMax(tborpm032Para.getThicknessMax());
        tborpm032.setInputType(tborpm032Para.getInputType());
        tborpm032.setOptionSeq(tborpm032Para.getOptionSeq());
        tborpm032.setMaterialThicknessMin(tborpm032Para.getMaterialThicknessMin());
        tborpm032.setMaterialThicknessMax(tborpm032Para.getMaterialThicknessMax());
        tborpm032.setDateCreate(LocalDateTime.now()); // 代入系統時間
        tborpm032.setUserCreate(tborpm032Para.getUserCreate());
        int flag = tborpm032Service.insert(tborpm032);
        log.info("add result : " + flag);
        return new ResultUtil<>().setData(flag);
    }

    @ApiOperation("批量新增")
    @RequestMapping(value = "/batchsaveData", method = RequestMethod.POST)
    public Result<Object> batchsaveData(@RequestBody List<ORPP032para> tborpm032Para) {
        StringBuilder INFO = new StringBuilder();
        LocalDateTime systime = LocalDateTime.now();
        for (int i = 0; i < tborpm032Para.size(); i++) {
            ORPP032 tborpm032 = new ORPP032();
            tborpm032.setPlantCode("TC");
            tborpm032.setProductType(tborpm032Para.get(i).getProductType());
            tborpm032.setThicknessMin(tborpm032Para.get(i).getThicknessMin());
            tborpm032.setThicknessMax(tborpm032Para.get(i).getThicknessMax());
            tborpm032.setInputType(tborpm032Para.get(i).getInputType());
            tborpm032.setOptionSeq(tborpm032Para.get(i).getOptionSeq());
            tborpm032.setMaterialThicknessMin(tborpm032Para.get(i).getMaterialThicknessMin());
            tborpm032.setMaterialThicknessMax(tborpm032Para.get(i).getMaterialThicknessMax());
            tborpm032.setDateCreate(systime); // 代入系統時間
            tborpm032.setUserCreate(tborpm032Para.get(i).getUserCreate());
            int flag = tborpm032Service.insert(tborpm032);
            INFO.append(",").append(flag);
        }
        log.info("add result : " + INFO);
        return new ResultUtil<>().setData(INFO.toString());
    }

    @ApiOperation("刪除所有資料")
    @RequestMapping(value = "/deleteallData", method = RequestMethod.POST)
    public boolean deleteallData(){
        boolean INFO =  tborpm032Service.deleteall();
        log.info("delete all : " + INFO);
        return INFO;
    }

}
