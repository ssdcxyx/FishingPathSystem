package edu.jxufe.tiamo.fishingpathsys.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "course", schema = "FishingPathSystem", catalog = "")
public class Course {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @Basic
    @Column(name = "course_logo_path")
    private String courseLogoPath;
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
    private int duration;
    @Basic
    @Column(name = "section_number")
    private short sectionNumber;
    @Basic
    @Column(name = "chapter_number")
    private short chapterNumber;
    @Basic
    @Column(name = "total_grade")
    private BigDecimal totalGrade;
    @Basic
    @Column(name = "practical_grade")
    private BigDecimal practicalGrade;
    @Basic
    @Column(name = "concise_grade")
    private BigDecimal conciseGrade;
    @Basic
    @Column(name = "clear_grade")
    private BigDecimal clearGrade;

    @ManyToOne(targetEntity = CourseType.class)
    @JoinColumn(name = "course_type_id",referencedColumnName = "id")
    private CourseType courseType;
    @OneToMany(targetEntity = CourseChapter.class,mappedBy = "course", fetch = FetchType.LAZY)
    private List<CourseChapter> courseChapterList;

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getCourseLogoPath() {
        return courseLogoPath;
    }

    public void setCourseLogoPath(String courseLogoPath) {
        this.courseLogoPath = courseLogoPath;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public short getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(short sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public short getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(short chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public BigDecimal getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(BigDecimal totalGrade) {
        this.totalGrade = totalGrade;
    }

    public BigDecimal getPracticalGrade() {
        return practicalGrade;
    }

    public void setPracticalGrade(BigDecimal practicalGrade) {
        this.practicalGrade = practicalGrade;
    }

    public BigDecimal getConciseGrade() {
        return conciseGrade;
    }

    public void setConciseGrade(BigDecimal conciseGrade) {
        this.conciseGrade = conciseGrade;
    }

    public BigDecimal getClearGrade() {
        return clearGrade;
    }

    public void setClearGrade(BigDecimal clearGrade) {
        this.clearGrade = clearGrade;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public List<CourseChapter> getCourseChapterList() {
        return courseChapterList;
    }

    public void setCourseChapterList(List<CourseChapter> courseChapterList) {
        this.courseChapterList = courseChapterList;
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
                sectionNumber == course.sectionNumber &&
                chapterNumber == course.chapterNumber &&
                Objects.equals(courseLogoPath,course.courseLogoPath)&&
                Objects.equals(name, course.name) &&
                Objects.equals(author, course.author) &&
                Objects.equals(description, course.description) &&
                Objects.equals(totalGrade, course.totalGrade) &&
                Objects.equals(practicalGrade, course.practicalGrade) &&
                Objects.equals(conciseGrade, course.conciseGrade) &&
                Objects.equals(clearGrade, course.clearGrade) &&
                Objects.equals(courseType,course.courseType) &&
                Objects.equals(courseChapterList, course.courseChapterList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, courseLogoPath, name, rank, author, description, learningNumber, duration, sectionNumber, chapterNumber, totalGrade, practicalGrade, conciseGrade, clearGrade, courseType, courseChapterList);
    }
}
