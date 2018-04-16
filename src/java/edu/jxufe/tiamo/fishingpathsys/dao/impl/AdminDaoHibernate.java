package edu.jxufe.tiamo.fishingpathsys.dao.impl;

import edu.jxufe.tiamo.common.dao.impl.BaseDaoHibernate;
import edu.jxufe.tiamo.fishingpathsys.dao.AdminDao;
import edu.jxufe.tiamo.fishingpathsys.domain.Admin;

import java.util.List;

public class AdminDaoHibernate extends BaseDaoHibernate<Admin> implements AdminDao{

    @Override
    public List<Admin> findAdminByUserId(short userId) {
        String hql = "FROM "+Admin.class.getSimpleName()+" where user_id=?0";
        List<Admin> adminList = find(hql,userId);
        return adminList;
    }
}
