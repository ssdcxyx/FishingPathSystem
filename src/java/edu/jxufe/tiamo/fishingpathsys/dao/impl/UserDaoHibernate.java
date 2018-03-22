package edu.jxufe.tiamo.fishingpathsys.dao.impl;

import edu.jxufe.tiamo.common.dao.impl.BaseDaoHibernate;
import edu.jxufe.tiamo.fishingpathsys.dao.UserDao;
import edu.jxufe.tiamo.fishingpathsys.domain.User;

import java.util.List;

public class UserDaoHibernate extends BaseDaoHibernate<User> implements UserDao {

    @Override
    public List<User> findUserByAccountAndPassword(String account,String password) {
        String hql = "FROM "+User.class.getSimpleName()+" where account=?0 and password=?1";
        List<User> userList = find(hql,account,password);
        return userList;
    }

    @Override
    public List<User> findUserByTelephone(String telephone) {
        String hql = "FROM "+User.class.getSimpleName()+" where account=?0";
        List<User> userList = find(hql,telephone);
        return userList;
    }
}
