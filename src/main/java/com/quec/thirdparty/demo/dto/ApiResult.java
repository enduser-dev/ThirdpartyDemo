package com.quec.thirdparty.demo.dto;

import io.swagger.annotations.ApiModelProperty;

public class ApiResult {

    @ApiModelProperty(value = "响应状态码", required = true)
    private Integer code;

    @ApiModelProperty(value = "响应消息")
    private String msg;


    public ApiResult() { }

    public ApiResult(Integer code) {
        this.code = code;
    }

    public ApiResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean resultIsSuccess(){
        return this.code == 200;
    }

    public static ApiResult Success(){
        return new ApiResult(200,"");
    }


    public ApiResult Error(){
        return new ApiResult(code,msg);
    }

    public static ApiResult Error(Integer code, String msg){
        return new ApiResult(code,msg);
    }

    public static ApiResult ErrorService(){
        return new ApiResult(5000,"服务异常");
    }
}
