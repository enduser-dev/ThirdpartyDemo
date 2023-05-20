package com.quec.thirdparty.demo.dto;

public class TokenDto extends ApiResult{
    private String openid;
    private Token access_token;
    private Token refresh_token;

    public TokenDto() {
    }

    public TokenDto(Integer code, String msg) {
        super(code, msg);
    }

    public TokenDto(String openid) {
        this.openid = openid;

        this.setCode(200);
        this.access_token = new Token(2);
        this.refresh_token = new Token(24);
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Token getAccess_token() {
        return access_token;
    }

    public void setAccess_token(Token access_token) {
        this.access_token = access_token;
    }

    public Token getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(Token refresh_token) {
        this.refresh_token = refresh_token;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TokenDto){
            TokenDto tokenDto = (TokenDto) obj;
            return tokenDto.getOpenid() == openid;
        }
        return super.equals(obj);
    }
}
