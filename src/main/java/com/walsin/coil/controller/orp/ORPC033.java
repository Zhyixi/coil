package com.walsin.coil.controller.orp;
import com.walsin.coil.common.result.Result;
import com.walsin.coil.common.utils.ResultUtil;
import com.walsin.coil.domain.entity.orp.ORPE033;
import com.walsin.coil.domain.model.orp.ORPD033;
import com.walsin.coil.service.orp.ORPS033;
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
@Api(tags = "原料策略表")
@RequestMapping("/requestTborpm033")
public class ORPC033 {
    @Autowired
    private ORPS033 Service;

    @ApiOperation("取得資料byXml")
    @RequestMapping(value = "/getAllFromXml", method = RequestMethod.POST)
    public Result<Object> getAllFromXml() {
        List<ORPE033> users = Service.getAllFromXml();
        return new ResultUtil<>().setData(users);
    }

    @ApiOperation("刪除byId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "輸入id",required = true)
    })
    @RequestMapping(value = "/deleteData", method = RequestMethod.POST)
    public Result<Object> deleteData(@RequestParam("id") Integer id) {
        int flag = Service.deleteById(id);
        log.info("delete result : "+flag);
        return new ResultUtil<>().setData(flag);
    }

    @ApiOperation("修改byId")
    @RequestMapping(value = "/updateData", method = RequestMethod.POST)
    public Result<Object> updateData(@RequestBody ORPD033 Para) {
        ORPE033 Data = new ORPE033() ;
        Data = Service.selectById(Para.getId());
        Data.setPlantCode("TC");
        Data.setDaysFromNowMin(Para.getDaysFromNowMin());
        Data.setDaysFromNowMax(Para.getDaysFromNowMax());
        Data.setPriorityContent(Para.getPriorityContent());
        Data.setDateUpdate(LocalDateTime.now()); // 代入系統時間
        Data.setUserUpdate(Para.getUserUpdate());
        int flag = Service.updateData(Data);
        log.info("update result : "+flag);
        return new ResultUtil<>().setData(flag);
    }

    @ApiOperation("單筆新增")
    @RequestMapping(value = "/saveData", method = RequestMethod.POST)
    public Result<Object> saveData(@RequestBody ORPD033 Para) {
        ORPE033 Data = new ORPE033();
        Data.setPlantCode("TC");
        Data.setDaysFromNowMin(Para.getDaysFromNowMin());
        Data.setDaysFromNowMax(Para.getDaysFromNowMax());
        Data.setPriorityContent(Para.getPriorityContent());
        Data.setDateCreate(LocalDateTime.now()); // 代入系統時間
        Data.setUserCreate(Para.getUserCreate());
        int flag = Service.insert(Data);
        log.info("add result : "+flag);
        return new ResultUtil<>().setData(flag);
    }

    @ApiOperation("批量新增")
    @RequestMapping(value = "/batchsaveData", method = RequestMethod.POST)
    public Result<Object> batchsaveData(@RequestBody List<ORPD033> Para) {
        StringBuilder INFO = new StringBuilder();
        LocalDateTime systime = LocalDateTime.now();
        for (int i = 0; i < Para.size(); i++) {
            ORPE033 Data = new ORPE033();
            Data.setPlantCode("TC");
            Data.setDaysFromNowMin(Para.get(i).getDaysFromNowMin());
            Data.setDaysFromNowMax(Para.get(i).getDaysFromNowMax());
            Data.setPriorityContent(Para.get(i).getPriorityContent());
            Data.setDateCreate(systime); // 代入系統時間
            Data.setUserCreate(Para.get(i).getUserCreate());
            int flag = Service.insert(Data);
            INFO.append(",").append(flag);
        }
        log.info("add result : " + INFO);
        return new ResultUtil<>().setData(INFO.toString());
    }

    @ApiOperation("刪除所有資料")
    @RequestMapping(value = "/deleteallData", method = RequestMethod.POST)
    public boolean deleteallData(){
        boolean INFO =  Service.deleteall();
        log.info("delete all : " + INFO);
        return INFO;
    }


}
