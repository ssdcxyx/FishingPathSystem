package edu.jxufe.tiamo.fishingpathsys.service;

import edu.jxufe.tiamo.fishingpathsys.domain.User;
import edu.jxufe.tiamo.util.Code;

public interface UserService {

    // 用户登录
    User userLogin(String account,String password);

    // 用户注册
    User userRegister(String userName,String telephone,String password,String type);

    // 发送手机验证码
    String sendVerificationCode(String telephone);

    // 验证手机验证码
    Code.jiguangReturnResult verifyVerificationCode(String msgId, String validStr);

    // 根据手机号查找用户
    User getUserByTelephone(String telephone);
}
