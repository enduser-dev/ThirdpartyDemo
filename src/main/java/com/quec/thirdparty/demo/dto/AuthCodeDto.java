package com.quec.thirdparty.demo.dto;

import io.swagger.annotations.ApiModelProperty;

public class AuthCodeDto extends ApiResult{

    @ApiModelProperty(value = "AuthCode", required = true)
    private String auth_code;

    public AuthCodeDto() {
    }

    public AuthCodeDto(Integer code, String msg) {
        super(code, msg);
    }

    public AuthCodeDto(Integer code, String msg, String auth_code) {
        super(code, msg);
        this.auth_code = auth_code;
    }

    public String getAuth_code() {
        return auth_code;
    }

    public void setAuth_code(String auth_code) {
        this.auth_code = auth_code;
    }
}
