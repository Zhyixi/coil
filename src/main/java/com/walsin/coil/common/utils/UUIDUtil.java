package com.walsin.coil.common.utils;

import java.util.UUID;

/**
 * @author Jane
 * @date 2022/11/21 下午 01:09
 */
public class UUIDUtil {
    private String uuid ;

    public String getUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
