package com.walsin.coil.common.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jane
 * @date 2022/11/21 下午 12:57
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L ;
    /**
     *成功標誌
     */
    private boolean success ;
    /**
     * 返回消息
     */
    private String message ;
    /**
     * 返回消息碼
     */
    private Integer code ;
    /***
     * 返回時間戳
     */
    private long timeStamp = System.currentTimeMillis();
    /***
     * 返回數據集
     */
    private T data ;
}
