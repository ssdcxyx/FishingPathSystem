package edu.jxufe.tiamo.fishingpathsys.domain.CourseLearningRecordDTO;

import edu.jxufe.tiamo.fishingpathsys.domain.Course;
import edu.jxufe.tiamo.fishingpathsys.domain.LearningRecord;

import java.util.List;

public class CourseDTO {

    private Course course;

    private List<LearningRecordDTO> learningRecordDTOList;

    private Integer completeness;

    public CourseDTO() {
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<LearningRecordDTO> getLearningRecordDTOList() {
        return learningRecordDTOList;
    }

    public void setLearningRecordDTOList(List<LearningRecordDTO> learningRecordDTOList) {
        this.learningRecordDTOList = learningRecordDTOList;
    }

    public Integer getCompleteness() {
        return completeness;
    }

    public void setCompleteness(Integer completeness) {
        this.completeness = completeness;
    }
}
