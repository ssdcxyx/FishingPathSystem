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

    public enum role{
        PUBLIC("游客",(short)0),
        STAFF("员工用户",(short)1),
        ENTERPRISE("企业用户",(short)2),
        ADMIN("管理员",(short)3);
        // 成员变量
        private String name;
        private short index;
        // 构造方法
        private role(String name, short index) {
            this.name = name;
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public short getIndex() {
            return index;
        }

        public void setIndex(short index) {
            this.index = index;
        }
    }

    public static String isExist = "isExist";
}
