package com.walsin.coil.controller.orp;

import com.walsin.coil.common.result.Result;
import com.walsin.coil.common.utils.ResultUtil;
import com.walsin.coil.domain.entity.orp.ORPP031;
import com.walsin.coil.domain.model.orp.ORPP031para;
import com.walsin.coil.service.orp.ORPP031Service;
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
@Api(tags = "訂單寬度原料對照表")
@RequestMapping("/requestTborpm031")
public class ORPP031Controller {
    @Autowired
    private ORPP031Service tborpm031Service;
    @ApiOperation("取得資料byService")
    @RequestMapping(value = "/getOrpData", method = RequestMethod.GET)
    public Result<Object> getOrpData() {
        List<ORPP031> users = tborpm031Service.findAll();
        log.info("test : {}",users);
        return new ResultUtil<>().setData(users);
    }

    @ApiOperation("取得資料byXml")
    @RequestMapping(value = "/getAllFromXml", method = RequestMethod.GET)
    public Result<Object> getAllFromXml() {
        List<ORPP031> users = tborpm031Service.getAllFromXml();
        return new ResultUtil<>().setData(users);
    }

    @ApiOperation("取得資料by註解")
    @RequestMapping(value = "/getAllFromSelect", method = RequestMethod.GET)
    public Result<Object> getAllFromSelect() {
        List<ORPP031> users = tborpm031Service.getAllFromSelect();
        return new ResultUtil<>().setData(users);
    }


    @ApiOperation("刪除byId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "輸入id",required = true)
    })
    @RequestMapping(value = "/deleteData", method = RequestMethod.POST)
    public Result<Object> deleteData(@RequestParam("id") Integer id) {
        int flag = tborpm031Service.deleteById(id);
        log.info("delete result : "+flag);
        return new ResultUtil<>().setData(flag);
    }




    @ApiOperation("修改byId")
    @RequestMapping(value = "/updateData", method = RequestMethod.POST)
    public Result<Object> updateData(@RequestBody ORPP031para tborpm031Para) {
        ORPP031 tborpm031 = new ORPP031() ;
        tborpm031 = tborpm031Service.selectById(tborpm031Para.getId());
        tborpm031.setPlantCode("TC");
        tborpm031.setSaleOrderWidthMin(tborpm031Para.getSaleOrderWidthMin());
        tborpm031.setSaleOrderWidthMax(tborpm031Para.getSaleOrderWidthMax());
        tborpm031.setProcessType(tborpm031Para.getProcessType());
        tborpm031.setMaterialWidthMin(tborpm031Para.getMaterialWidthMin());
        tborpm031.setMaterialWidthMax(tborpm031Para.getMaterialWidthMax());
        tborpm031.setTolerateMin(tborpm031Para.getTolerateMin());
        tborpm031.setTolerateMax(tborpm031Para.getTolerateMax());
        tborpm031.setDateUpdate(LocalDateTime.now()); // 代入系統時間
        tborpm031.setUserUpdate(tborpm031Para.getUserUpdate());
        int flag = tborpm031Service.updateData(tborpm031);
        log.info("update result : "+flag);
        return new ResultUtil<>().setData(flag);
    }

    @ApiOperation("單筆新增")
    @RequestMapping(value = "/saveData", method = RequestMethod.POST)
    public Result<Object> saveData(@RequestBody ORPP031para tborpm031Para) {
        ORPP031 tborpm031 = new ORPP031();
        tborpm031.setPlantCode("TC");
        tborpm031.setSaleOrderWidthMin(tborpm031Para.getSaleOrderWidthMin());
        tborpm031.setSaleOrderWidthMax(tborpm031Para.getSaleOrderWidthMax());
        tborpm031.setProcessType(tborpm031Para.getProcessType());
        tborpm031.setMaterialWidthMin(tborpm031Para.getMaterialWidthMin());
        tborpm031.setMaterialWidthMax(tborpm031Para.getMaterialWidthMax());
        tborpm031.setTolerateMin(tborpm031Para.getTolerateMin());
        tborpm031.setTolerateMax(tborpm031Para.getTolerateMax());
        tborpm031.setDateCreate(LocalDateTime.now()); // 代入系統時間
        tborpm031.setUserCreate(tborpm031Para.getUserCreate());
        int flag = tborpm031Service.insert(tborpm031);
        log.info("add result : "+flag);
        return new ResultUtil<>().setData(flag);
    }

    @ApiOperation("批量新增")
    @RequestMapping(value = "/batchsaveData", method = RequestMethod.POST)
    public Result<Object> batchsaveData(@RequestBody List<ORPP031para> tborpm031Para) {
        StringBuilder INFO = new StringBuilder();
        LocalDateTime systime = LocalDateTime.now();
        for (int i = 0; i < tborpm031Para.size(); i++) {
            ORPP031 tborpm031 = new ORPP031();
            tborpm031.setPlantCode("TC");
            tborpm031.setSaleOrderWidthMin(tborpm031Para.get(i).getSaleOrderWidthMin());
            tborpm031.setSaleOrderWidthMax(tborpm031Para.get(i).getSaleOrderWidthMax());
            tborpm031.setProcessType(tborpm031Para.get(i).getProcessType());
            tborpm031.setMaterialWidthMin(tborpm031Para.get(i).getMaterialWidthMin());
            tborpm031.setMaterialWidthMax(tborpm031Para.get(i).getMaterialWidthMax());
            tborpm031.setTolerateMin(tborpm031Para.get(i).getTolerateMin());
            tborpm031.setTolerateMax(tborpm031Para.get(i).getTolerateMax());
            tborpm031.setDateCreate(systime); // 代入系統時間
            tborpm031.setUserCreate(tborpm031Para.get(i).getUserCreate());
            int flag = tborpm031Service.insert(tborpm031);
            INFO.append(",").append(flag);
        }
        log.info("add result : " + INFO);
        return new ResultUtil<>().setData(INFO.toString());
    }

    @ApiOperation("刪除所有資料")
    @RequestMapping(value = "/deleteallData", method = RequestMethod.POST)
    public boolean deleteallData(){
        boolean INFO =  tborpm031Service.deleteall();
        log.info("delete all : " + INFO);
        return INFO;
    }


}
