package com.walsin.coil.controller.orp;
import com.walsin.coil.common.result.Result;
import com.walsin.coil.common.utils.ResultUtil;
import com.walsin.coil.common.utils.TableUtil;
import com.walsin.coil.domain.model.orp.ORPP100para;
import com.walsin.coil.service.orp.ORPP100Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.tablesaw.api.Table;


/**
 * @author ur08366
 * @date 2022/12/15 下午 3:30
 */
@Slf4j //簡化JAVA程式碼，為類提供一個名為log的log4j日誌物件(lombok)
@RestController //
@Api(tags = "配料邏輯模組")
@RequestMapping("/requestTborpm100")
public class ORPP100Controller {
    @Autowired
    private ORPP100Service tborpm100Service;
    @ApiOperation("廠內配料執行")
    @RequestMapping(value = "/orderMaching", method = RequestMethod.POST)
    public Result<Object> test_func(@RequestBody ORPP100para data) throws Exception {
        String customer = data.getCustomer();
        String gradeNo = data.getGradeNo();
        String product = data.getProduct();
        Integer width = data.getWidth();
        Float weightMin = data.getWeightMin();
        Float weightMax = data.getWeightMax();
        Float thicknessMin = data.getThicknessMin();
        Float thicknessMax = data.getThicknessMax();
        Float materialThicknessMin = data.getThicknessMin();
        Float materialThicknessMax = data.getMaterialThicknessMax();
        String Trimming = data.getTrimming();
        String JIS = data.getJIS();
        String certificate = data.getCertificate();
        String expectDate = data.getExpectDate();
        String plantCode = data.getPlantCode();
        String userName = data.getUserName();
        String dateTime = data.getDateTime();
        Table result = tborpm100Service.orderMatching(customer,gradeNo,product,width, weightMin, weightMax,thicknessMin,thicknessMax,materialThicknessMin,materialThicknessMax, Trimming,JIS,certificate,expectDate,plantCode,userName,dateTime);
        String obj = TableUtil.ConvertToJson(result);
        return new ResultUtil<>().setData(obj);
    }
}
