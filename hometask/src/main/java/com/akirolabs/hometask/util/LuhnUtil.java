package com.akirolabs.hometask.util;

import org.springframework.stereotype.Component;

@Component
public class LuhnUtil {
    public int getLuhnValue(String number) {
        int sum = 0;
        boolean alternate = false;
        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(number.charAt(i));
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
            alternate = !alternate;
        }
        int checksum = (10 - (sum % 10)) % 10;
        return checksum;
    }

    public Boolean validateLuhn(String token) {
        token = token.replace("-", "");
        int tokenLength = token.length();
        int sum = 0;
        boolean alternate = false;
        for (int i = tokenLength - 1; i >= 0; i--) {
            int d = token.charAt(i) - '0';
            if (alternate) {
                d = d * 2;
            }
            sum += d / 10;
            sum += d % 10;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}
