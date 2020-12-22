package com.zanlab.grade.utils;

import java.util.Random;

public class CommonUtils {
    /**
     * 返回指定长度的字符串，包括大小写字母和数字
     * @param length 字符串长度
     * @return
     */
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
