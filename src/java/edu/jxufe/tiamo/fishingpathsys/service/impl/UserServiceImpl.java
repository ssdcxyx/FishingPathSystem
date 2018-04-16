package edu.jxufe.tiamo.fishingpathsys.service.impl;

import edu.jxufe.tiamo.fishingpathsys.dao.AdminDao;
import edu.jxufe.tiamo.fishingpathsys.dao.EnterpriseDao;
import edu.jxufe.tiamo.fishingpathsys.dao.StaffDao;
import edu.jxufe.tiamo.fishingpathsys.dao.UserDao;
import edu.jxufe.tiamo.fishingpathsys.domain.Admin;
import edu.jxufe.tiamo.fishingpathsys.domain.Enterprise;
import edu.jxufe.tiamo.fishingpathsys.domain.Staff;
import edu.jxufe.tiamo.fishingpathsys.domain.User;
import edu.jxufe.tiamo.fishingpathsys.exception.CustomException;
import edu.jxufe.tiamo.fishingpathsys.service.UserService;
import edu.jxufe.tiamo.util.Code;
import edu.jxufe.tiamo.util.ListUtil;
import edu.jxufe.tiamo.util.OtherAPIRequestUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private EnterpriseDao enterpriseDao;
    private StaffDao staffDao;
    private AdminDao adminDao;

    // 为业务逻辑组件依赖注入DAO组件所需要的setter方法
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setEnterpriseDao(EnterpriseDao enterpriseDao) {
        this.enterpriseDao = enterpriseDao;
    }

    public void setStaffDao(StaffDao staffDao) {
        this.staffDao = staffDao;
    }

    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
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
            return new ListUtil<User>().getFirst(userDao.findUserByTelephone(telephone));
        }catch (Exception ex){
            ex.printStackTrace();
            throw new CustomException("根据手机号查找用户时出现异常，请通知管理员！");
        }
    }

    @Override
    public Object userLogin(String telephone, String password) {
        try{
            List<User> userList = userDao.findUserByAccountAndPassword(telephone,password);
            if(userList.size()>0){
                User user = userList.get(0);
                if(user.getRole() == Code.role.STAFF.getIndex()){
                    return new ListUtil<Staff>().getFirst(staffDao.findStaffByUserId(user.getId()));
                }else if(user.getRole() == Code.role.ENTERPRISE.getIndex()){
                    return new ListUtil<Enterprise>().getFirst(enterpriseDao.findEnterpriseByUserId(user.getId()));
                }else if(user.getRole() == Code.role.ADMIN.getIndex()){
                    return new ListUtil<Admin>().getFirst(adminDao.findAdminByUserId(user.getId()));
                }
            }
            return null;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new CustomException("用户登录时出现问题，请通知管理员！");
        }
    }
}
