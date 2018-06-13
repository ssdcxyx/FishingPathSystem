package edu.jxufe.tiamo.fishingpathsys.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "learning_record", schema = "FishingPathSystem")
public class LearningRecord implements Serializable{

    @Basic
    @Column(name = "last_learning_time")
    private String lastLearningTime;
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
    @JsonBackReference
    @ManyToOne(targetEntity = LearningPath.class)
    @JoinColumn(name = "learning_path_id", referencedColumnName = "id", nullable = false)
    private LearningPath learningPath;
    @ManyToOne(targetEntity = CourseSection.class)
    @JoinColumn(name = "course_section_id", referencedColumnName = "id", nullable = false)
    private CourseSection courseSection;
    @ManyToOne(targetEntity = CourseChapter.class)
    @JoinColumn(name = "course_chapter_id", referencedColumnName = "id", nullable = false)
    private CourseChapter courseChapter;

    public String getLastLearningTime() {
        return lastLearningTime;
    }

    public void setLastLearningTime(String lastLearningTime) {
        this.lastLearningTime = lastLearningTime;
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

    public LearningPath getLearningPath() {
        return learningPath;
    }

    public void setLearningPath(LearningPath learningPath) {
        this.learningPath = learningPath;
    }

    public CourseSection getCourseSection() {
        return courseSection;
    }

    public void setCourseSection(CourseSection courseSection) {
        this.courseSection = courseSection;
    }

    public CourseChapter getCourseChapter() {
        return courseChapter;
    }

    public void setCourseChapter(CourseChapter courseChapter) {
        this.courseChapter = courseChapter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LearningRecord that = (LearningRecord) o;
        return
                lastLearningTime == that.lastLearningTime &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(grade, that.grade) &&
                Objects.equals(course, that.course) &&
                Objects.equals(learningPath,that.learningPath) &&
                Objects.equals(courseSection, that.courseSection) &&
                Objects.equals(courseChapter, that.courseChapter);
    }

    @Override
    public int hashCode() {

        return Objects.hash(lastLearningTime, courseSection, startTime, endTime, grade, course, learningPath, courseChapter);
    }
}
