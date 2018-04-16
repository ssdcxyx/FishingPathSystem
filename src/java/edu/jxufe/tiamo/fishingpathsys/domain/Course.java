package edu.jxufe.tiamo.fishingpathsys.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "course",schema = "FishingPathSystem")
public class Course {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "rank")
    private short rank;
    @Basic
    @Column(name = "author")
    private String author;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "learning_number")
    private int learningNumber;
    @Basic
    @Column(name = "duration")
    private short duration;
    @Basic
    @Column(name = "chapter_number")
    private short chapterNumber;

    @ManyToOne(targetEntity = CourseType.class)
    @JoinColumn(name = "course_type_id", referencedColumnName = "id",nullable = false)
    private CourseType courseType;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private List<LearningRecord> learningRecordList;

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getRank() {
        return rank;
    }

    public void setRank(short rank) {
        this.rank = rank;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLearningNumber() {
        return learningNumber;
    }

    public void setLearningNumber(int learningNumber) {
        this.learningNumber = learningNumber;
    }

    public short getDuration() {
        return duration;
    }

    public void setDuration(short duration) {
        this.duration = duration;
    }

    public short getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(short chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public List<LearningRecord> getLearningRecordList() {
        return learningRecordList;
    }

    public void setLearningRecordList(List<LearningRecord> learningRecordList) {
        this.learningRecordList = learningRecordList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id &&
                rank == course.rank &&
                learningNumber == course.learningNumber &&
                duration == course.duration &&
                chapterNumber == course.chapterNumber &&
                Objects.equals(name, course.name) &&
                Objects.equals(author, course.author) &&
                Objects.equals(description, course.description) &&
                Objects.equals(courseType,course.courseType) &&
                Objects.equals(learningRecordList,course.learningRecordList);

    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, rank, author, description, learningNumber, duration, chapterNumber, courseType, learningRecordList);
    }
}
