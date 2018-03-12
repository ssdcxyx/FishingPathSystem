package edu.jxufe.tiamo.fishingpathsys.service.impl;

import edu.jxufe.tiamo.fishingpathsys.dao.StaffInfoDao;
import edu.jxufe.tiamo.fishingpathsys.dao.UserDao;
import edu.jxufe.tiamo.fishingpathsys.domain.StaffInfo;
import edu.jxufe.tiamo.fishingpathsys.domain.User;
import edu.jxufe.tiamo.fishingpathsys.service.UserService;

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

    @Override
    public User userLogin(String account, String password) {
        List<User> userList = userDao.findUserByAccountAndPassword(account,password);
        if(userList.size()>0){
            User user = userList.get(0);
            if(user!=null){
                return user;
            }
        }
        return null;
    }

    @Override
    public Boolean userRegister(User user) {
        return null;
    }
}
