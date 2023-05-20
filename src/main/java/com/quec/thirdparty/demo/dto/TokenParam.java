package com.quec.thirdparty.demo.dto;

import io.swagger.annotations.ApiModelProperty;

public class TokenParam {

    @ApiModelProperty(value = "授权类型", required = true)
    private String grant_type;

    @ApiModelProperty(value = "鉴权的 AppKey", required = true)
    private String app_key;

    @ApiModelProperty(value = "AuthCode", required = true)
    private String auth_code;

    @ApiModelProperty(value = "签名", required = true)
    private String sign;

    @ApiModelProperty(value = "刷新token", required = true)
    private String refresh_token;

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getApp_key() {
        return app_key;
    }

    public void setApp_key(String app_key) {
        this.app_key = app_key;
    }

    public String getAuth_code() {
        return auth_code;
    }

    public void setAuth_code(String auth_code) {
        this.auth_code = auth_code;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public TokenDto validate(){
        if(grant_type == null || grant_type.isEmpty()){
            return new TokenDto(100000,"grant_type 无效");
        }
        if(app_key == null || app_key.isEmpty()){
            return new TokenDto(100001,"app_key 或者 app_secret 无效");
        }
        if(grant_type.equals("auth_code")){
            if(auth_code == null || auth_code.isEmpty()){
                return new TokenDto(100002,"auth_code 无效");
            }
            if(sign == null || sign.isEmpty()){
                return new TokenDto(100003,"sign 无效");
            }
        }
        else if(grant_type.equals("refresh_token")){
            if(refresh_token == null || refresh_token.isEmpty()){
                return new TokenDto(100004," refresh_token 无效");
            }
        }
        else{
            return new TokenDto(100000,"grant_type 无效");
        }
        return new TokenDto(200,"");
    }
}
