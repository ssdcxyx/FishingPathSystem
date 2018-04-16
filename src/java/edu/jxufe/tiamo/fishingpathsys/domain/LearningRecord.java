package edu.jxufe.tiamo.fishingpathsys.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "learning_record", schema = "FishingPathSystem")
public class LearningRecord implements Serializable{

    @Basic
    @Column(name = "learning_time")
    private short learningTime;
    @Basic
    @Column(name = "learning_chapter")
    private short learningChapter;
    @Basic
    @Column(name = "start_time")
    private String startTime;
    @Basic
    @Column(name = "end_time")
    private String endTime;
    @Basic
    @Column(name = "grade")
    private BigDecimal grade;

    @Id
    @ManyToOne(targetEntity = Course.class)
    @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)
    private Course course;
    @Id
    @ManyToOne(targetEntity = Staff.class)
    @JoinColumn(name = "staff_id", referencedColumnName = "id", nullable = false)
    private Staff staff;
    @Id
    @ManyToOne(targetEntity = LearningPath.class)
    @JoinColumn(name = "learning_path_id", referencedColumnName = "id", nullable = false)
    private LearningPath learningPath;

    public short getLearningTime() {
        return learningTime;
    }

    public void setLearningTime(short learningTime) {
        this.learningTime = learningTime;
    }

    public short getLearningChapter() {
        return learningChapter;
    }

    public void setLearningChapter(short learningChapter) {
        this.learningChapter = learningChapter;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getGrade() {
        return grade;
    }

    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public LearningPath getLearningPath() {
        return learningPath;
    }

    public void setLearningPath(LearningPath learningPath) {
        this.learningPath = learningPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LearningRecord that = (LearningRecord) o;
        return
                learningTime == that.learningTime &&
                learningChapter == that.learningChapter &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(grade, that.grade) &&
                Objects.equals(course, that.course) &&
                Objects.equals(staff, that.staff) &&
                Objects.equals(learningPath,that.learningPath);
    }

    @Override
    public int hashCode() {

        return Objects.hash(learningTime, learningChapter, startTime, endTime, grade, course, staff, learningPath);
    }
}
