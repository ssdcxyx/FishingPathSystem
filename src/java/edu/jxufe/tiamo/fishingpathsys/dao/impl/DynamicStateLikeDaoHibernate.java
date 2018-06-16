package edu.jxufe.tiamo.fishingpathsys.dao.impl;

import edu.jxufe.tiamo.common.dao.impl.BaseDaoHibernate;
import edu.jxufe.tiamo.fishingpathsys.dao.DynamicStateLikeDao;
import edu.jxufe.tiamo.fishingpathsys.domain.DynamicStateLike;

import java.util.List;

public class DynamicStateLikeDaoHibernate extends BaseDaoHibernate<DynamicStateLike> implements DynamicStateLikeDao{

    @Override
    public List<DynamicStateLike> findDynamicStateLikeByUserIdAndDynamicStateId(Short userId, Short dynamicStateId) {
        String hql = "FROM " + DynamicStateLike.class.getSimpleName() + " where user_id =?0 and  dynamic_state_id =?1";
        return find(hql,userId,dynamicStateId);
    }
}
