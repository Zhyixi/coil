package com.walsin.coil.domain.model.login;

import lombok.Data;

@Data
public class CAS {

    private String username;
    private String password;
    private String fromapp;
    private String env;

    private boolean isAuth = false;
    private int statusCode ;
    private String ticket;

    private String res;
}
