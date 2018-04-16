package edu.jxufe.tiamo.fishingpathsys.service;

import edu.jxufe.tiamo.fishingpathsys.domain.Enterprise;
import edu.jxufe.tiamo.fishingpathsys.domain.User;
import edu.jxufe.tiamo.util.Code;

public interface UserService {

    // 发送手机验证码
    String sendVerificationCode(String telephone);

    // 验证手机验证码
    Code.jiguangReturnResult verifyVerificationCode(String msgId, String validStr);

    // 根据手机号查找用户
    User getUserByTelephone(String telephone);

    // 用户登录
    Object userLogin(String telephone,String password);
}
