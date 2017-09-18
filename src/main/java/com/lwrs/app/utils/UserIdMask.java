package com.lwrs.app.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

public class UserIdMask {
    private static final int MULTIPLIER = 131;

    public static String maskUserId(Long userId){
        if(null == userId || userId < 1){
            return "";
        }

        userId = userId * MULTIPLIER;
        String temp = String.valueOf(userId);

        Random random = new Random();
        int swapLen = random.nextInt(temp.length());
        swapLen = (0 == swapLen) ? 1 : swapLen;
        swapLen = (swapLen > 9) ? 9 : swapLen;

        StringBuilder sb = new StringBuilder();
        sb.append(swapLen)
            .append(temp.substring(swapLen))
            .append(temp.substring(0, swapLen));
        return sb.toString();
    }

    public static Long unmask(String str){
        if(StringUtils.isEmpty(str) || !StringUtils.isNumeric(str) || str.startsWith("0")){
            return null;
        }
        int swapLen = Integer.valueOf(str.substring(0, 1));
        if(str.length() -1 < swapLen ){
            return null;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(str.substring(str.length() - swapLen))
            .append(str.substring(1, str.length() - swapLen));

        Long temp = Long.valueOf(sb.toString());
        if(null== temp || 0 != temp % MULTIPLIER){
            return null;
        }
        return temp/MULTIPLIER;
    }


}
