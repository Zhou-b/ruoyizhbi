package com.yan2b.web.model.entity;

import javax.validation.constraints.NotNull;

/**
 * @program: ruoyizhbi
 * @description: User
 * @author: ZhouBing
 * @create: 2021-09-29 10:23
 **/
public class User {
    @NotNull(message = "name不能为null")
    String name;

    @NotNull
    String info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
