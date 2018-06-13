package edu.jxufe.tiamo.fishingpathsys.dao;

import edu.jxufe.tiamo.common.dao.BaseDao;
import edu.jxufe.tiamo.fishingpathsys.domain.LearningRecord;

import java.math.BigDecimal;
import java.util.List;

public interface LearningRecordDao extends BaseDao<LearningRecord>{

    List<LearningRecord> getLearningRecordByCourseIdAndLearningPathId(Short courseId, Short learningPathId);

    List<LearningRecord> getLearningRecordByLearningPathId(Short learningPathId);
}
