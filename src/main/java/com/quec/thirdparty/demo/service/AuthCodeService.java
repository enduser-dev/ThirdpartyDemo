package com.quec.thirdparty.demo.service;

import com.quec.thirdparty.demo.dto.AuthCodeDto;
import com.quec.thirdparty.demo.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthCodeService {
    private List<String> authCodeList = new ArrayList<>();

    public AuthCodeDto getAuthCode() {
        String authCode = Utils.generateRandomString(32);
        authCodeList.add(authCode);
        return new AuthCodeDto(200, "", authCode);
    }

    public Boolean checkAuthCode(String authCode){
        int idx = authCodeList.indexOf(authCode);
        if(idx == -1){
            return false;
        }
        authCodeList.remove(idx);
        return true;
    }
}
