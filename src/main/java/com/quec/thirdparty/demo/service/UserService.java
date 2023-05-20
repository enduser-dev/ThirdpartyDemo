package com.quec.thirdparty.demo.service;

import com.quec.thirdparty.demo.dto.TokenDto;
import com.quec.thirdparty.demo.dto.UserinfoDto;
import com.quec.thirdparty.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    TokenService tokenService;

    public UserinfoDto userinfo(String accessToken){
        // 验证 accessToken
        TokenDto tokenDto = tokenService.getInfoByAccessToken(accessToken);
        if(!tokenDto.resultIsSuccess()){
            return new UserinfoDto(tokenDto.getCode(),tokenDto.getMsg());
        }

        // 查询用户信息，实际项目通过 accessToken 查询对应用户的昵称，按照中间部分替换为 * 处理后返回脱敏后的用户昵称
        String nickname = tokenDto.getOpenid();
        String newNickname = Utils.desensitizedNickname(nickname);

        return new UserinfoDto(newNickname);
    }
}
