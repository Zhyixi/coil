package com.walsin.coil.common.constant;

/**
 * @author Jane
 * 返回结果可以定义在此处
 * @date 2022/11/24 下午 03:50
 */
public enum ResultEnum {
    SECURITY_NEED_LOGIN(401,"未授權的請求，請先登錄") ,
    SECURITY_APPID_ERROR(400,"無效的客戶端,請聯繫管理員確認") ,
    SECURITY_SIGN_ERROR(401,"簽名無效，請聯繫管理員") ,
    SECURITY_LOGIN_ERROR(500,"用戶名或密碼錯誤") ;


    public Integer code ;
    public String message ;

    ResultEnum(Integer code,String message){
        this.code = code ;
        this.message = message ;
    }
}
