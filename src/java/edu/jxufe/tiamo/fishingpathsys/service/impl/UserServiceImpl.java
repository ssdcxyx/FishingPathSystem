package edu.jxufe.tiamo.fishingpathsys.service.impl;

import com.google.gson.Gson;
import edu.jxufe.tiamo.fishingpathsys.dao.StaffInfoDao;
import edu.jxufe.tiamo.fishingpathsys.dao.UserDao;
import edu.jxufe.tiamo.fishingpathsys.domain.StaffInfo;
import edu.jxufe.tiamo.fishingpathsys.domain.User;
import edu.jxufe.tiamo.fishingpathsys.exception.CustomException;
import edu.jxufe.tiamo.fishingpathsys.service.UserService;
import edu.jxufe.tiamo.util.Code;
import edu.jxufe.tiamo.util.OtherAPIRequestUtil;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private StaffInfoDao staffInfoDao;

    // 为业务逻辑组件依赖注入DAO组件所需要的setter方法
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setStaffInfoDao(StaffInfoDao staffInfoDao) {
        this.staffInfoDao = staffInfoDao;
    }

    /**
     * 用户登录函数
     * @param account 用户账号
     * @param password 用户密码
     * @return
     */
    @Override
    public User userLogin(String account, String password) {
        try{
            List<User> userList = userDao.findUserByAccountAndPassword(account,password);
            if(userList.size()>0){
                User user = userList.get(0);
                if(user!=null){
                    return user;
                }
            }
            return null;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new CustomException("用户登录时出现异常，请通知管理员！");
        }

    }

    /**
     * 用户注册
     * @param userName 用户名
     * @param telephone 手机号码即账号
     * @param password 密码
     * @param type 用户类型
     * @return
     */
    @Override
    public Integer userRegister(String userName, String telephone, String password, String type) {
        try{
            StaffInfo staffInfo = new StaffInfo(userName,0);
            staffInfoDao.save(staffInfo);
            User user = new User(telephone,password,type,staffInfo);
            return (Integer) userDao.save(user);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new CustomException("用户注册时出现异常，请通知管理员！");
        }
    }

    /**
     * 发送手机验证码
     * @param telephone 手机号码
     */
    @Override
    public String sendVerificationCode(String telephone){
        try{
            return OtherAPIRequestUtil.sendPhoneVerificationCodeUseJiGuang(telephone);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new CustomException("发送手机验证码时出现异常，请通知管理员！");
        }
    }

    /**
     * 验证手机验证码
     * @param msgId 请求验证码返回的信息
     * @param validStr 用户输入的验证码
     * @return
     */
    @Override
    public Code.jiguangReturnResult verifyVerificationCode(String msgId, String validStr) {
        try{
            return OtherAPIRequestUtil.verifySendValidSMSCode(msgId,validStr);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new CustomException("验证手机验证码时出现异常，请通知管理员！");
        }

    }

    @Override
    public User getUserByTelephone(String telephone) {
        try{
            List<User> userList = userDao.findUserByTelephone(telephone);
            if(userList.size()>0){
                User user = userList.get(0);
                if(user!=null){
                    return user;
                }
            }
            return null;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new CustomException("根据手机号查找用户时出现异常，请通知管理员！");
        }
    }
}
