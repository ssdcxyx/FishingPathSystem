package edu.jxufe.tiamo.fishingpathsys.dao.impl;

import edu.jxufe.tiamo.common.dao.impl.BaseDaoHibernate;
import edu.jxufe.tiamo.fishingpathsys.dao.DynamicStateDao;
import edu.jxufe.tiamo.fishingpathsys.domain.Course;
import edu.jxufe.tiamo.fishingpathsys.domain.DynamicState;

import java.util.List;

public class DynamicStateDaoHibernate extends BaseDaoHibernate<DynamicState> implements DynamicStateDao {

    @Override
    public List<DynamicState> getDynamicStatesByUserId(Short userId) {
        String hql = "FROM "+DynamicState.class.getSimpleName() +" where user_id =?0";
        List<DynamicState> dynamicStateList = find(hql,userId);
        return dynamicStateList;
    }
}
