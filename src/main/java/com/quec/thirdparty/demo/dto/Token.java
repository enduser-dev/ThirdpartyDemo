package com.quec.thirdparty.demo.dto;

import com.quec.thirdparty.demo.utils.Utils;

import java.util.Calendar;

public class Token {
    private String token;
    private Long expiration_time;

    public Token() {
    }

    /**
     * @param hour token 在当前时间后的失效小时数
     */
    public Token(Integer hour) {
        // 生成 token，可以根据自己系统标准来实现
        this.token = Utils.generateRandomString(64);

        // token 失效时间，以秒为单位的时间戳
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR,hour);
        this.expiration_time = calendar.getTimeInMillis() / 1000;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpiration_time() {
        return expiration_time;
    }

    public void setExpiration_time(Long expiration_time) {
        this.expiration_time = expiration_time;
    }
}
