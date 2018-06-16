package edu.jxufe.tiamo.fishingpathsys.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "course_type", schema = "FishingPathSystem", catalog = "")
public class CourseType {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "description")
    private String description;

    @ManyToOne(targetEntity = CourseCategory.class)
    @JoinColumn(name = "course_category_id", referencedColumnName = "id")
    private CourseCategory courseCategory;
    @JsonBackReference
    @OneToMany(targetEntity = Course.class,mappedBy = "courseType",fetch = FetchType.LAZY)
    private List<Course> courseList;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CourseCategory getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(CourseCategory courseCategory) {
        this.courseCategory = courseCategory;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseType that = (CourseType) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(courseCategory, that.courseCategory) &&
                Objects.equals(courseList, that.courseList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, courseCategory, courseList);
    }
}
