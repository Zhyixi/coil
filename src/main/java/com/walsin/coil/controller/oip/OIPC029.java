package com.walsin.coil.controller.oip;
import com.walsin.coil.common.result.Result;
import com.walsin.coil.common.utils.ResultUtil;
import com.walsin.coil.domain.entity.oip.OIPE029;
import com.walsin.coil.domain.model.oip.OIPD029;
import com.walsin.coil.service.oip.OIPS029;
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
@Api(tags = "接單係數表")
@RequestMapping("/requestTboipm029")
public class OIPC029 {
    @Autowired
    private OIPS029 Service;

    @ApiOperation("取得資料byXml")
    @RequestMapping(value = "/getAllFromXml", method = RequestMethod.POST)
    public Result<Object> getAllFromXml() {
        List<OIPE029> users = Service.getAllFromXml();
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
    public Result<Object> updateData(@RequestBody OIPD029 Para) {
        OIPE029 Data = new OIPE029() ;
        Data = Service.selectById(Para.getId());
        Data.setPlantCode("TC");
        Data.setProductType(Para.getProductType());
        Data.setCondition1(Para.getCondition1());
        Data.setCondition2(Para.getCondition2());
        Data.setCondition3(Para.getCondition3());
        Data.setCondition4(Para.getCondition4());
        Data.setCondition5(Para.getCondition5());
        Data.setCoefficientMill3(Para.getCoefficientMill3());
        Data.setCoefficientMill4(Para.getCoefficientMill4());
        Data.setCoefficientMill5(Para.getCoefficientMill5());
        Data.setDateUpdate(LocalDateTime.now()); // 代入系統時間
        Data.setUserUpdate(Para.getUserUpdate());
        int flag = Service.updateData(Data);
        log.info("update result : "+flag);
        return new ResultUtil<>().setData(flag);
    }

    @ApiOperation("單筆新增")
    @RequestMapping(value = "/saveData", method = RequestMethod.POST)
    public Result<Object> saveData(@RequestBody OIPD029 Para) {
        OIPE029 Data = new OIPE029();
        Data.setPlantCode("TC");
        Data.setProductType(Para.getProductType());
        Data.setCondition1(Para.getCondition1());
        Data.setCondition2(Para.getCondition2());
        Data.setCondition3(Para.getCondition3());
        Data.setCondition4(Para.getCondition4());
        Data.setCondition5(Para.getCondition5());
        Data.setCoefficientMill3(Para.getCoefficientMill3());
        Data.setCoefficientMill4(Para.getCoefficientMill4());
        Data.setCoefficientMill5(Para.getCoefficientMill5());
        Data.setDateCreate(LocalDateTime.now()); // 代入系統時間
        Data.setUserCreate(Para.getUserCreate());
        int flag = Service.insert(Data);
        log.info("add result : "+flag);
        return new ResultUtil<>().setData(flag);
    }

    @ApiOperation("批量新增")
    @RequestMapping(value = "/batchsaveData", method = RequestMethod.POST)
    public Result<Object> batchsaveData(@RequestBody List<OIPD029> Para) {
        StringBuilder INFO = new StringBuilder();
        LocalDateTime systime = LocalDateTime.now();
        for (int i = 0; i < Para.size(); i++) {
            OIPE029 Data = new OIPE029();
            Data.setPlantCode("TC");
            Data.setProductType(Para.get(i).getProductType());
            Data.setCondition1(Para.get(i).getCondition1());
            Data.setCondition2(Para.get(i).getCondition2());
            Data.setCondition3(Para.get(i).getCondition3());
            Data.setCondition4(Para.get(i).getCondition4());
            Data.setCondition5(Para.get(i).getCondition5());
            Data.setCoefficientMill3(Para.get(i).getCoefficientMill3());
            Data.setCoefficientMill4(Para.get(i).getCoefficientMill4());
            Data.setCoefficientMill5(Para.get(i).getCoefficientMill5());
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
