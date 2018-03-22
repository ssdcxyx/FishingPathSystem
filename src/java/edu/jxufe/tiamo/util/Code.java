package edu.jxufe.tiamo.util;

// 返回结果编码
public class Code {

    // 极光推送返回验证结果
    public enum jiguangReturnResult {
        SUCCESS,
        ERROR,
        LOSE, // 失效
        NULL,
        REPEAT, // 重复
        TRIED // 已经验证
    }

    public static String isExist = "isExist";
}
