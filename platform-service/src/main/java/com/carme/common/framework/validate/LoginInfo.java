package com.carme.common.framework.validate;

import java.io.Serializable;

public class LoginInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 138523423421L;

    private Long              id;                                     //当前登录账号ID

    private String            email;                                   //当前登录账号

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
