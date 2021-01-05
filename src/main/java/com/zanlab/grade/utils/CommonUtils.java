package com.zanlab.grade.utils;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CommonUtils {
    /**
     * 返回指定长度的字符串，包括大小写字母和数字
     * @param length 字符串长度
     * @return
     */
    public static String getRandomString(int length){
        //从大写小写数字中随机
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 计算grade列表的成绩平均值，返回精度0.01的平均
     * @param scoreList 分数列表
     * @param fairweight 截取多少尾巴
     * @return 0.01精度的平均值
     */
    public static double calculateAverage(List<Double> scoreList, int fairweight){
        int num=scoreList.size();
        if(num==0)return 0;
        //如果数量不够就不截尾巴
        if(num<=fairweight*2){
            return calculateAverage(scoreList,0);
        }
        //排序
        Collections.sort(scoreList);
        //一直截尾巴
        while(fairweight!=0){
            scoreList.remove(0);
            scoreList.remove(scoreList.size()-1);
            fairweight--;
        }
        double average=0.00d;
        //计算平均值
        for(Double e:scoreList){
            average+=e/scoreList.size();
        }
        //格式化输出
        DecimalFormat df = new DecimalFormat("######0.00");
        return Double.parseDouble(df.format(average));
    }

}
