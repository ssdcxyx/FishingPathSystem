package edu.jxufe.tiamo.fishingpathsys.service.impl;

import edu.jxufe.tiamo.fishingpathsys.dao.*;
import edu.jxufe.tiamo.fishingpathsys.domain.*;
import edu.jxufe.tiamo.fishingpathsys.exception.CustomException;
import edu.jxufe.tiamo.fishingpathsys.service.UserService;
import edu.jxufe.tiamo.util.Code;
import edu.jxufe.tiamo.util.ListUtil;
import edu.jxufe.tiamo.util.Logger;
import edu.jxufe.tiamo.util.OtherAPIRequestUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private EnterpriseDao enterpriseDao;
    private StaffDao staffDao;
    private AdminDao adminDao;
    private DynamicStateDao dynamicStateDao;

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

    public void setDynamicStateDao(DynamicStateDao dynamicStateDao) {
        this.dynamicStateDao = dynamicStateDao;
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
            Logger.Log.error("getAllStaffsByEnterpriseIdAndDepartmentId:"+ex);
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
            Logger.Log.error("verifyVerificationCode:"+ex);
            ex.printStackTrace();
            throw new CustomException("验证手机验证码时出现异常，请通知管理员！");
        }

    }

    @Override
    public User getUserByTelephone(String telephone) {
        try{
            return new ListUtil<User>().getFirst(userDao.findUserByTelephone(telephone));
        }catch (Exception ex){
            Logger.Log.error("getUserByTelephone:"+ex);
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
            Logger.Log.error("userLogin:"+ex);
            ex.printStackTrace();
            throw new CustomException("用户登录时出现异常，请通知管理员！");
        }
    }

    @Override
    public List<DynamicState> getAllDynamicStatesByEnterpriseId(Short enterpriseId) {
        try{
            List<DynamicState> dynamicStates = new ArrayList<>();
            List<Admin> adminList = adminDao.findAll(Admin.class);
            for (Admin admin : adminList) {
                dynamicStates.addAll(dynamicStateDao.getDynamicStatesByUserId(admin.getUser().getId()));
            }
            Enterprise enterprise = enterpriseDao.get(Enterprise.class,enterpriseId);
            dynamicStates.addAll(dynamicStateDao.getDynamicStatesByUserId(enterprise.getUser().getId()));
            List<Staff> staffList = staffDao.findStaffsByEnterpriseId(enterpriseId);
            for (Staff staff : staffList) {
                dynamicStates.addAll(dynamicStateDao.getDynamicStatesByUserId(staff.getUser().getId()));
            }
            ListSort(dynamicStates);
            return dynamicStates;
        }catch (Exception ex){
            Logger.Log.error("getAllDynamicStatesByEnterpriseId:"+ex);
            ex.printStackTrace();
            throw new CustomException("获取动态时出现异常，请联系管理员！");
        }
    }

    // 根据时间排序
    private static void ListSort(List<DynamicState> list) {
        Collections.sort(list, new Comparator<DynamicState>() {
            @Override
            public int compare(DynamicState o1, DynamicState o2) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date dt1 = format.parse(o1.getTime());
                    Date dt2 = format.parse(o2.getTime());
                    if (dt1.getTime() > dt2.getTime()) {
                        return -1;
                    } else if (dt1.getTime() < dt2.getTime()) {
                        return 0;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }
}
