package com.quec.thirdparty.demo.service;

import com.quec.thirdparty.demo.dto.TokenDto;
import com.quec.thirdparty.demo.dto.TokenParam;
import com.quec.thirdparty.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TokenService {

    private List<TokenDto> tokenList = new ArrayList<>();
    private Map<String, Integer> accessTokenMap = new HashMap<>();
    private Map<String, Integer> refreshTokenMap = new HashMap<>();

    @Autowired
    AuthCodeService authCodeService;

    @Value("${app.key}")
    String appKey;

    @Value("${app.secret}")
    String appSecret;

    private TokenDto generateToken(String openid) {
        TokenDto tokenDto = new TokenDto(openid);

        // 记录 token
        int idx = tokenList.size();
        tokenList.add(tokenDto);
        accessTokenMap.put(tokenDto.getAccess_token().getToken(), idx);
        refreshTokenMap.put(tokenDto.getRefresh_token().getToken(), idx);

        return tokenDto;
    }

    private TokenDto authCode(TokenParam param){
        // 校验 auth_code，同一个 auth_code 只能使用一次
        if (!authCodeService.checkAuthCode(param.getAuth_code())) {
            return new TokenDto(100002, "auth_code 无效");
        }

        // 验证签名
        if (!Utils.checkSign(appKey, appSecret, param.getAuth_code(), param.getSign())) {
            return new TokenDto(100003, "sign 无效");
        }

        // openid 是第三方用户平台中的用户唯一标识，移远 APP SDK 会通过该标识来识别用户
        // 这使用 auth_code 模拟，实际项目中要替换为 auth_code 对应的用户唯一标识
        String openid = param.getAuth_code();

        // 用户之前是否已换取过 token
        List<TokenDto> findTokenList = tokenList.stream().filter(e -> e.getOpenid().equals(openid)).collect(Collectors.toList());
        if(findTokenList != null && findTokenList.size() > 0){
            // 清理之前的 token
            for(TokenDto oldToken : findTokenList){
                tokenList.remove(oldToken);
            }
        }

        return generateToken(openid);
    }

    private TokenDto refreshToken(TokenParam param){
        // 验证 refresh_token
        if (!refreshTokenMap.containsKey(param.getRefresh_token())) {
            return new TokenDto(100004, "refresh_token 无效");
        }

        // 清理之前的 token
        int idx = refreshTokenMap.get(param.getRefresh_token());
        TokenDto oldToken = tokenList.get(idx);
        refreshTokenMap.remove(param.getRefresh_token());
        accessTokenMap.remove(oldToken.getAccess_token());

        return generateToken(oldToken.getOpenid());
    }

    public TokenDto token(TokenParam param) {
        // 验证参数
        TokenDto paramValidate = param.validate();
        if (!paramValidate.resultIsSuccess()) {
            return paramValidate;
        }

        // 使用 auth_code 换取 token
        if (param.getGrant_type().equals("auth_code")) {
            return authCode(param);
        }
        // 使用 refresh_token 刷新 token
        else if (param.getGrant_type().equals("refresh_token")) {
            return refreshToken(param);
        }
        return new TokenDto(100005, "未知异常");
    }

    public TokenDto tokenByNotValidate(TokenParam param) {
        return generateToken(param.getAuth_code());
    }

    public TokenDto getInfoByAccessToken(String accessToken){
        if(!accessTokenMap.containsKey(accessToken)){
            return new TokenDto(100000, "token 无效");
        }
        int idx = accessTokenMap.get(accessToken);
        return tokenList.get(idx);
    }
}
