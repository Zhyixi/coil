package com.walsin.coil.controller.orp;

import com.walsin.coil.common.result.Result;
import com.walsin.coil.common.utils.ResultUtil;
import com.walsin.coil.domain.entity.orp.ORPP030;
import com.walsin.coil.domain.model.orp.ORPP030para;
import com.walsin.coil.service.orp.ORPP030Service;
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
@Api(tags = "訂單鋼種原料對照表")
@RequestMapping("/requestTborpm030")
public class ORPP030Controller {
    @Autowired
    private ORPP030Service tborpm030Service;
    @ApiOperation("取得資料byService")
    @RequestMapping(value = "/getOrpData", method = RequestMethod.GET)
    public Result<Object> getOrpData() {
        List<ORPP030> users = tborpm030Service.findAll();
        log.info("test : {}",users);
        return new ResultUtil<>().setData(users);
    }

    @ApiOperation("取得資料byXml")
    @RequestMapping(value = "/getAllFromXml", method = RequestMethod.GET)
    public Result<Object> getAllFromXml() {
        List<ORPP030> users = tborpm030Service.getAllFromXml();
        return new ResultUtil<>().setData(users);
    }

    @ApiOperation("取得資料by註解")
    @RequestMapping(value = "/getAllFromSelect", method = RequestMethod.GET)
    public Result<Object> getAllFromSelect() {
        List<ORPP030> users = tborpm030Service.getAllFromSelect();
        return new ResultUtil<>().setData(users);
    }


    @ApiOperation("刪除byId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "輸入id",required = true)
    })
    @RequestMapping(value = "/deleteData", method = RequestMethod.POST)
    public Result<Object> deleteData(@RequestParam("id") Integer id) {
        int flag = tborpm030Service.deleteById(id);
        log.info("delete result : "+flag);
        return new ResultUtil<>().setData(flag);
    }




    @ApiOperation("修改byId")
    @RequestMapping(value = "/updateData", method = RequestMethod.POST)
    public Result<Object> updateData(@RequestBody ORPP030para tborpm030Para) {
        ORPP030 tborpm030 = new ORPP030() ;
        tborpm030 = tborpm030Service.selectById(tborpm030Para.getId());
        tborpm030.setPlantCode("TC");
        tborpm030.setSaleOrderGradeNo(tborpm030Para.getSaleOrderGradeNo());
        tborpm030.setMaterialGradeNo(tborpm030Para.getMaterialGradeNo());
        tborpm030.setPurchaseFlag(tborpm030Para.getPurchaseFlag());
        tborpm030.setOptionSeq(tborpm030Para.getOptionSeq());
        tborpm030.setDateUpdate(LocalDateTime.now()); // 代入系統時間
        tborpm030.setUserUpdate(tborpm030Para.getUserUpdate());
        int flag = tborpm030Service.updateData(tborpm030);
        log.info("update result : "+flag);
        return new ResultUtil<>().setData(flag);
    }

    @ApiOperation("單筆新增")
    @RequestMapping(value = "/saveData", method = RequestMethod.POST)
    public Result<Object> saveData(@RequestBody ORPP030para tborpm030Para) {
        ORPP030 tborpm030 = new ORPP030();
        tborpm030.setPlantCode("TC");
        tborpm030.setSaleOrderGradeNo(tborpm030Para.getSaleOrderGradeNo());
        tborpm030.setMaterialGradeNo(tborpm030Para.getMaterialGradeNo());
        tborpm030.setPurchaseFlag(tborpm030Para.getPurchaseFlag());
        tborpm030.setOptionSeq(tborpm030Para.getOptionSeq());
        tborpm030.setDateCreate(LocalDateTime.now()); // 代入系統時間
        tborpm030.setUserCreate(tborpm030Para.getUserCreate());
        int flag = tborpm030Service.insert(tborpm030);
        log.info("add result : "+flag);
        return new ResultUtil<>().setData(flag);
    }

    @ApiOperation("批量新增")
    @RequestMapping(value = "/batchsaveData", method = RequestMethod.POST)
    public Result<Object> batchsaveData(@RequestBody List<ORPP030para> tborpm030Para) {
        StringBuilder INFO = new StringBuilder();
        LocalDateTime systime = LocalDateTime.now();
        for (int i = 0; i < tborpm030Para.size(); i++) {
            ORPP030 tborpm030 = new ORPP030();
            tborpm030.setPlantCode("TC");
            tborpm030.setSaleOrderGradeNo(tborpm030Para.get(i).getSaleOrderGradeNo());
            tborpm030.setMaterialGradeNo(tborpm030Para.get(i).getMaterialGradeNo());
            tborpm030.setPurchaseFlag(tborpm030Para.get(i).getPurchaseFlag());
            tborpm030.setOptionSeq(tborpm030Para.get(i).getOptionSeq());
            tborpm030.setDateCreate(systime); // 代入系統時間
            tborpm030.setUserCreate(tborpm030Para.get(i).getUserCreate());
            int flag = tborpm030Service.insert(tborpm030);
            INFO.append(",").append(flag);
        }
        log.info("add result : " + INFO);
        return new ResultUtil<>().setData(INFO.toString());
    }

    @ApiOperation("刪除所有資料")
    @RequestMapping(value = "/deleteallData", method = RequestMethod.POST)
    public boolean deleteallData(){
        boolean INFO =  tborpm030Service.deleteall();
        log.info("delete all : " + INFO);
        return INFO;
    }


}
