package edu.jxufe.tiamo.fishingpathsys.dao.impl;

import edu.jxufe.tiamo.common.dao.impl.BaseDaoHibernate;
import edu.jxufe.tiamo.fishingpathsys.dao.LearningRecordDao;
import edu.jxufe.tiamo.fishingpathsys.domain.LearningRecord;

import java.math.BigDecimal;
import java.util.List;

public class LearningRecordDaoHibernate extends BaseDaoHibernate<LearningRecord> implements LearningRecordDao {

    @Override
    public List<LearningRecord> getLearningRecordByCourseIdAndLearningPathId(Short courseId, Short learningPathId) {
        String hql = "FROM " + LearningRecord.class.getSimpleName() + " where course_id=?0 and learning_path_id=?1";
        return find(hql,courseId,learningPathId);
    }

    @Override
    public List<LearningRecord> getLearningRecordByLearningPathId(Short learningPathId) {
        String hql = "FROM " + LearningRecord.class.getSimpleName() + " where learning_path_id=?0";
        return find(hql,learningPathId);
    }
}
