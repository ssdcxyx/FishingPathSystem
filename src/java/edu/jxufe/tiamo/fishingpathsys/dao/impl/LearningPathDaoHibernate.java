package edu.jxufe.tiamo.fishingpathsys.dao.impl;

import edu.jxufe.tiamo.common.dao.impl.BaseDaoHibernate;
import edu.jxufe.tiamo.fishingpathsys.dao.LearningPathDao;
import edu.jxufe.tiamo.fishingpathsys.domain.LearningPath;

import java.util.List;

public class LearningPathDaoHibernate extends BaseDaoHibernate<LearningPath> implements LearningPathDao {

    @Override
    public List<LearningPath> getLearningPathsByStaffId(Short staffId) {
        String hql = "FROM " + LearningPath.class.getSimpleName() + " where staff_id=?0";
        return find(hql,staffId);
    }

    @Override
    public List<LearningPath> getLearningPathByStaffIdAndCourseCategoryId(Short staffId, Short courseCategoryId) {
        String hql = "FROM " + LearningPath.class.getSimpleName() + " where staff_id=?0 and course_category_id=?1";
        return find(hql,staffId,courseCategoryId);
    }
}
