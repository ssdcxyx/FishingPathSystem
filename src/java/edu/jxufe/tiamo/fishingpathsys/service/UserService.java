package edu.jxufe.tiamo.fishingpathsys.service;

import edu.jxufe.tiamo.fishingpathsys.domain.User;

public interface UserService {

    // 用户登录
    User userLogin(String account,String password);

    // 用户注册
    Boolean userRegister(User user);

    // 发送验证码
    void sendVerificationCode(String telephone,String verificationCode) throws Exception;
}
