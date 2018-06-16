package edu.jxufe.tiamo.fishingpathsys.domain.CourseLearningRecordDTO;

import edu.jxufe.tiamo.fishingpathsys.domain.LearningPath;
import edu.jxufe.tiamo.fishingpathsys.domain.LearningRecord;

public class LearningRecordDTO {

    private LearningRecord learningRecord;

    private LearningPath learningPath;

    public LearningRecordDTO() {
    }

    public LearningRecord getLearningRecord() {
        return learningRecord;
    }

    public void setLearningRecord(LearningRecord learningRecord) {
        this.learningRecord = learningRecord;
    }

    public LearningPath getLearningPath() {
        return learningPath;
    }

    public void setLearningPath(LearningPath learningPath) {
        this.learningPath = learningPath;
    }
}
