package edu.jxufe.tiamo.fishingpathsys.domain.StaffDepartmentDTO;

import edu.jxufe.tiamo.fishingpathsys.domain.LearningPath;
import edu.jxufe.tiamo.fishingpathsys.domain.Staff;

import java.util.List;

public class StaffDTO {

    private Staff staff;

    private List<LearningPath> learningPathList;

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public List<LearningPath> getLearningPathList() {
        return learningPathList;
    }

    public void setLearningPathList(List<LearningPath> learningPathList) {
        this.learningPathList = learningPathList;
    }
}
