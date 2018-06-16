package edu.jxufe.tiamo.fishingpathsys.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "course_chapter", schema = "FishingPathSystem", catalog = "")
public class CourseChapter {
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

    @JsonBackReference
    @ManyToOne(targetEntity = Course.class)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @OneToMany(targetEntity = CourseSection.class, mappedBy = "courseChapter", fetch = FetchType.LAZY)
    private List<CourseSection> courseSectionList;

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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<CourseSection> getCourseSectionList() {
        return courseSectionList;
    }

    public void setCourseSectionList(List<CourseSection> courseSectionList) {
        this.courseSectionList = courseSectionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseChapter that = (CourseChapter) o;
        return id == that.id &&
                serialNumber == that.serialNumber &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(course, that.course) &&
                Objects.equals(courseSectionList, that.courseSectionList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, serialNumber, name, description, course, courseSectionList);
    }
}
