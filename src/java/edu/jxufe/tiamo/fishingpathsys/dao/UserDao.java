package edu.jxufe.tiamo.fishingpathsys.dao;

import edu.jxufe.tiamo.common.dao.BaseDao;
import edu.jxufe.tiamo.fishingpathsys.domain.User;

import java.util.List;


public interface UserDao extends BaseDao<User> {

    // 根据账号和密码查找用户
    List<User> findUserByAccountAndPassword(String account,String password);

    // 根据手机号查找用户
    List<User> findUserByTelephone(String telephone);
}
