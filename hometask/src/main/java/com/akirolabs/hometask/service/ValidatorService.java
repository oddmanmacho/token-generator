package com.akirolabs.hometask.service;

import com.akirolabs.hometask.util.LuhnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidatorService {

    @Autowired
    LuhnUtil luhnUtil;

    public Boolean validateTheToken(String token) {
        int checksum = luhnUtil.getLuhnValue(token.substring(0, token.length() - 1));
        int lastDigit = Character.getNumericValue(token.charAt(token.length() - 1));
        return (checksum == lastDigit);
    }

    public Boolean validateToken(final String token) {
        return luhnUtil.validateLuhn(token);
    }


}
