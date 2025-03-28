package com.quec.thirdparty.demo.controller;

import com.quec.thirdparty.demo.dto.AuthCodeDto;
import com.quec.thirdparty.demo.dto.TokenDto;
import com.quec.thirdparty.demo.dto.TokenParam;
import com.quec.thirdparty.demo.dto.UserinfoDto;
import com.quec.thirdparty.demo.service.AuthCodeService;
import com.quec.thirdparty.demo.service.TokenService;
import com.quec.thirdparty.demo.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/oauth")
@Api(tags = {"OAuth"})
public class OauthController {

    @Autowired
    AuthCodeService authCodeService;

    @Autowired
    TokenService tokenService;

    @Autowired
    UserService userService;

    @ApiOperation(value = "获取 AuthCode", notes = "获取 AuthCode<br/>request header 中需要添加 Authorization 进行鉴权，Authorization 的值可以随意填写")
    @ApiResponses({
            @ApiResponse(code = 200, message = "获取 AuthCode成功"),
            @ApiResponse(code = 100000, message = "token 无效")
    })
    @GetMapping("/authcode")
    public AuthCodeDto authcode(@RequestHeader(name = "Authorization") String token) {
        if (token == null || token.isEmpty()) {
            return new AuthCodeDto(100000, "token 无效");
        }

        // TODO：验证 token 是否有效

        return authCodeService.getAuthCode();
    }

    @ApiOperation(value = "换取 token", notes = "换取 token")
    @ApiResponses({
            @ApiResponse(code = 200, message = "换取 token 成功"),
            @ApiResponse(code = 100000, message = "grant_type 无效"),
            @ApiResponse(code = 100001, message = "app_key 或者 app_secret 无效"),
            @ApiResponse(code = 100002, message = "auth_code 无效"),
            @ApiResponse(code = 100003, message = "refresh_token 无效"),
            @ApiResponse(code = 100004, message = "未知异常")
    })
    @PostMapping("/token")
    public TokenDto token(@RequestBody TokenParam param) {
        return tokenService.token(param);
    }

    @ApiOperation(value = "换取 token，不验证参数", notes = "换取 token，不验证参数")
    @ApiResponses({
            @ApiResponse(code = 200, message = "换取 token 成功"),
            @ApiResponse(code = 100000, message = "grant_type 无效"),
            @ApiResponse(code = 100001, message = "app_key 或者 app_secret 无效"),
            @ApiResponse(code = 100002, message = "auth_code 无效"),
            @ApiResponse(code = 100003, message = "refresh_token 无效"),
            @ApiResponse(code = 100004, message = "未知异常")
    })
    @PostMapping("/tokenByNotValidate")
    public TokenDto tokenByNotValidate(@RequestBody TokenParam param) {
        return tokenService.tokenByNotValidate(param);
    }

    @ApiOperation(value = "查询用户信息", notes = "查询用户信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询用户信息成功"),
            @ApiResponse(code = 100000, message = "token 无效")
    })
    @GetMapping("/userinfo")
    public UserinfoDto userinfo(@RequestHeader(name = "Authorization") String accessToken) {
        if (accessToken == null || accessToken.isEmpty()) {
            return new UserinfoDto(100000, "token 无效");
        }
        return userService.userinfo(accessToken);
    }
}
