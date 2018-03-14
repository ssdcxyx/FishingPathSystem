package edu.jxufe.tiamo.util;

public class StringUtil {

    // 生成随机6位数
    public static String getRandom6(){
        return (int)((Math.random()*9+1)*100000)+"";
    }
}
