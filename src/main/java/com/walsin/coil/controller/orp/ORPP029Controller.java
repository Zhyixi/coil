package com.walsin.coil.controller.orp;
import com.walsin.coil.common.result.Result;
import com.walsin.coil.common.utils.ResultUtil;
import com.walsin.coil.domain.entity.orp.ORPP029;
import com.walsin.coil.domain.model.orp.ORPP029para;
import com.walsin.coil.service.orp.ORPP029Service;
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
@Api(tags = "客戶鋼種廠內鋼種對應表")
@RequestMapping("/requestTborpm029")
public class ORPP029Controller {
    @Autowired
    private ORPP029Service tborpm029Service;
    @ApiOperation("取得資料byService")
    @RequestMapping(value = "/getOrpData", method = RequestMethod.GET)
    public Result<Object> getOrpData() {
        List<ORPP029> users = tborpm029Service.findAll();
        log.info("test : {}",users);
        return new ResultUtil<>().setData(users);
    }

    @ApiOperation("取得資料byXml")
    @RequestMapping(value = "/getAllFromXml", method = RequestMethod.GET)
    public Result<Object> getAllFromXml() {
        List<ORPP029> users = tborpm029Service.getAllFromXml();
        return new ResultUtil<>().setData(users);
    }

    @ApiOperation("取得資料by註解")
    @RequestMapping(value = "/getAllFromSelect", method = RequestMethod.GET)
    public Result<Object> getAllFromSelect() {
        List<ORPP029> users = tborpm029Service.getAllFromSelect();
        return new ResultUtil<>().setData(users);
    }


    @ApiOperation("刪除byId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "輸入id",required = true)
    })
    @RequestMapping(value = "/deleteData", method = RequestMethod.POST)
    public Result<Object> deleteData(@RequestParam("id") Integer id) {
        int flag = tborpm029Service.deleteById(id);
        log.info("delete result : "+flag);
        return new ResultUtil<>().setData(flag);
    }




    @ApiOperation("修改byId")
    @RequestMapping(value = "/updateData", method = RequestMethod.POST)
    public Result<Object> updateData(@RequestBody ORPP029para tborpm029Para) {
        ORPP029 tborpm029 = new ORPP029() ;
        tborpm029 = tborpm029Service.selectById(tborpm029Para.getId());
        tborpm029.setPlantCode("TC");
        tborpm029.setProductType(tborpm029Para.getProductType());
        tborpm029.setCustGradeNo(tborpm029Para.getCustGradeNo());
        tborpm029.setSaleOrderGradeNo(tborpm029Para.getSaleOrderGradeNo());
        tborpm029.setDateUpdate(LocalDateTime.now()); // 代入系統時間
        tborpm029.setUserUpdate(tborpm029Para.getUserUpdate());
        int flag = tborpm029Service.updateData(tborpm029);
        log.info("update result : "+flag);
        return new ResultUtil<>().setData(flag);
    }

    @ApiOperation("單筆新增")
    @RequestMapping(value = "/saveData", method = RequestMethod.POST)
    public Result<Object> saveData(@RequestBody ORPP029para tborpm029Para) {
        ORPP029 tborpm029 = new ORPP029();
        tborpm029.setPlantCode("TC");
        tborpm029.setProductType(tborpm029Para.getProductType());
        tborpm029.setCustGradeNo(tborpm029Para.getCustGradeNo());
        tborpm029.setSaleOrderGradeNo(tborpm029Para.getSaleOrderGradeNo());
        tborpm029.setDateCreate(LocalDateTime.now()); // 代入系統時間
        tborpm029.setUserCreate(tborpm029Para.getUserCreate());
        int flag = tborpm029Service.insert(tborpm029);
        log.info("add result : "+flag);
        return new ResultUtil<>().setData(flag);
    }

    @ApiOperation("批量新增")
    @RequestMapping(value = "/batchsaveData", method = RequestMethod.POST)
    public Result<Object> batchsaveData(@RequestBody List<ORPP029para> tborpm029Para) {
        StringBuilder INFO = new StringBuilder();
        LocalDateTime systime = LocalDateTime.now();
        for (int i = 0; i < tborpm029Para.size(); i++) {
            ORPP029 tborpm029 = new ORPP029();
            tborpm029.setPlantCode("TC");
            tborpm029.setProductType(tborpm029Para.get(i).getProductType());
            tborpm029.setCustGradeNo(tborpm029Para.get(i).getCustGradeNo());
            tborpm029.setSaleOrderGradeNo(tborpm029Para.get(i).getSaleOrderGradeNo());
            tborpm029.setDateCreate(systime); // 代入系統時間
            tborpm029.setUserCreate(tborpm029Para.get(i).getUserCreate());
            int flag = tborpm029Service.insert(tborpm029);
            INFO.append(",").append(flag);
        }
        log.info("add result : " + INFO);
        return new ResultUtil<>().setData(INFO.toString());
    }

    @ApiOperation("刪除所有資料")
    @RequestMapping(value = "/deleteallData", method = RequestMethod.POST)
    public boolean deleteallData(){
        boolean INFO =  tborpm029Service.deleteall();
        log.info("delete all : " + INFO);
        return INFO;
    }


}
