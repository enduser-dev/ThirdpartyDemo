package com.quec.thirdparty.demo.dto;

public class UserinfoDto extends ApiResult{
    private String nick_name;

    public UserinfoDto() {
    }

    public UserinfoDto(Integer code, String msg) {
        super(code, msg);
    }

    public UserinfoDto(String nick_name) {
        this.setCode(200);
        this.setMsg("");
        this.nick_name = nick_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }
}
