package com.walsin.coil.common.utils;

import com.walsin.coil.common.constant.ResultCodeConstant;
import com.walsin.coil.common.result.Result;
import com.walsin.coil.common.result.ResultPage;


/**
 * @author Jane
 * @date 2022/11/21 下午 12:58
 */
public class ResultUtil<T> {
    private Result<T> result;
    private ResultPage<T> resultPage ;
    public ResultUtil(){
        result = new Result<>();
        result.setSuccess(true);
        result.setMessage("success");
        result.setCode(ResultCodeConstant.SuccessCode);

        resultPage = new ResultPage<>();
        resultPage.setSuccess(true);
        resultPage.setMessage("success");
        resultPage.setCode(ResultCodeConstant.SuccessCode);
        resultPage.setPageNum(1);
        resultPage.setTotalPage(1);
        resultPage.setTotalCount(0);

    }
    public Result<T> setData(T t){
        this.result.setData(t);
        this.result.setCode(ResultCodeConstant.SuccessCode);
        return this.result ;
    }
    public Result<T> setData(T t,String msg){
        this.result.setData(t);
        this.result.setCode(ResultCodeConstant.SuccessCode);
        this.result.setMessage(msg);
        return this.result;
    }
    public Result<T> setSuccessMsg(String msg){
        this.result.setSuccess(true);
        this.result.setMessage(msg);
        this.result.setCode(ResultCodeConstant.SuccessCode);
        this.result.setData(null);
        return this.result;
    }
    public Result<T> setErrorMsg(String msg){
        this.result.setSuccess(false);
        this.result.setCode(ResultCodeConstant.ErrorCode);
        this.result.setMessage(msg);
        return this.result;
    }
    public Result<T> setErrorMsg(Integer code,String msg){
        this.result.setSuccess(false);
        this.result.setCode(ResultCodeConstant.ErrorCode);
        this.result.setMessage(msg);
        return this.result ;
    }
    public ResultPage<T> setPageErrorMsg(String msg){
        this.resultPage.setSuccess(false);
        this.resultPage.setCode(ResultCodeConstant.ErrorCode);
        this.resultPage.setMessage(msg);
        return this.resultPage;
    }
    public ResultPage<T> setPageData(T t,Integer pageNum, Integer totalPage,Integer totalCount){
        this.resultPage.setPageNum(pageNum);
        this.resultPage.setTotalPage(totalPage);
        this.resultPage.setTotalCount(totalCount);
        this.resultPage.setData(t);
        this.resultPage.setCode(ResultCodeConstant.SuccessCode);
        return this.resultPage ;
    }

}