package edu.jxufe.tiamo.fishingpathsys.dao;

import edu.jxufe.tiamo.common.dao.BaseDao;
import edu.jxufe.tiamo.fishingpathsys.domain.LearningPath;

import java.util.List;

public interface LearningPathDao extends BaseDao<LearningPath> {

    List<LearningPath> getLearningPathsByStaffId(Short staffId);

    List<LearningPath> getLearningPathByStaffIdAndCourseCategoryId(Short staffId,Short courseCategoryId);
}
