package edu.jxufe.tiamo.fishingpathsys.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "course_section", schema = "FishingPathSystem", catalog = "")
public class CourseSection {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @Basic
    @Column(name = "serial_number")
    private short serialNumber;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "duration")
    private int duration;
    @Basic
    @Column(name = "file_address")
    private String fileAddress;

    @JsonBackReference
    @ManyToOne(targetEntity = CourseChapter.class)
    @JoinColumn(name = "course_chapter_id", referencedColumnName = "id")
    private CourseChapter courseChapter;
    @JsonBackReference
    @OneToMany(targetEntity = LearningRecord.class, mappedBy = "courseSection",fetch = FetchType.LAZY)
    private List<LearningRecord> learningRecords;


    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public short getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(short serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public CourseChapter getCourseChapter() {
        return courseChapter;
    }

    public void setCourseChapter(CourseChapter courseChapter) {
        this.courseChapter = courseChapter;
    }

    public List<LearningRecord> getLearningRecords() {
        return learningRecords;
    }

    public void setLearningRecords(List<LearningRecord> learningRecords) {
        this.learningRecords = learningRecords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseSection that = (CourseSection) o;
        return id == that.id &&
                serialNumber == that.serialNumber &&
                duration == that.duration &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(fileAddress, that.fileAddress) &&
                Objects.equals(courseChapter, that.courseChapter) &&
                Objects.equals(learningRecords, that.learningRecords);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, serialNumber, name, description, duration, fileAddress, courseChapter, learningRecords);
    }
}
