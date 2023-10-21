package com.akirolabs.hometask.service;

import com.akirolabs.hometask.util.LuhnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class GeneratorService {

    @Autowired
    LuhnUtil luhnUtil;

    public String generateToken(String digitsAllowed) throws Exception {
        if (!isValidInput(digitsAllowed)) {
            throw new IllegalArgumentException("Allowed digits must be digits (0-9).");
        }

        Random random = new Random();
        StringBuilder token = new StringBuilder();

        digitsAllowed = digitsAllowed.replace(",", "");
        // Generate a 15-digit random token
        for (int i = 0; i <= 15; i++) {
            if (i > 0 && i % 4 == 0) {
                token.append("-");
            }
            int index = random.nextInt(digitsAllowed.length());
            char digit = digitsAllowed.charAt(index);
            token.append(digit);
        }

        // int checksum = luhnUtil.getLuhnValue(token.toString());
        // return token.append(checksum).toString();
        return token.toString();
    }

    private boolean isValidInput(String input) {
        String[] parts = input.split(",");
        return Arrays.stream(parts)
                .map(String::trim)
                .allMatch(part -> {
                    try {
                        int digit = Integer.parseInt(part);
                        return digit >= 0 && digit <= 9;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                });
    }


}
