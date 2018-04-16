package edu.jxufe.tiamo.fishingpathsys.dao.impl;

import edu.jxufe.tiamo.common.dao.impl.BaseDaoHibernate;
import edu.jxufe.tiamo.fishingpathsys.dao.EnterpriseDao;
import edu.jxufe.tiamo.fishingpathsys.domain.Enterprise;

import java.util.List;

public class EnterpriseDaoHibernate extends BaseDaoHibernate<Enterprise> implements EnterpriseDao {

    @Override
    public List<Enterprise> findEnterpriseByUserId(short userId) {
        String hql = "FROM "+Enterprise.class.getSimpleName()+" where user_id=?0";
        List<Enterprise> enterpriseList = find(hql,userId);
        return enterpriseList;
    }
}
