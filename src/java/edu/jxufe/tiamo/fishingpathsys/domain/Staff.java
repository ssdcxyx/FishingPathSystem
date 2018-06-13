package edu.jxufe.tiamo.fishingpathsys.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.tools.javac.comp.Enter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "staff",schema = "FishingPathSystem")
public class Staff {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @Basic
    @Column(name = "job_number")
    private String jobNumber;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "telephone")
    private String telephone;
    @Basic
    @Column(name = "sex")
    private String sex;

    @ManyToOne(targetEntity = Enterprise.class)
    @JoinColumn(name = "enterprise_id",referencedColumnName = "id",nullable = false)
    private Enterprise enterprise;
    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id",referencedColumnName = "id",unique = true)
    private User user;
    @ManyToOne(targetEntity = PostType.class)
    @JoinColumn(name = "post_type_id", referencedColumnName = "id", nullable = false)
    private PostType postType;
    @ManyToOne(targetEntity = Department.class)
    @JoinColumn(name = "department_id",referencedColumnName = "id",nullable = false)
    private Department department;
    @JsonBackReference
    @OneToMany(targetEntity = LearningPath.class, mappedBy = "staff",fetch = FetchType.LAZY)
    private List<LearningPath> learningPathList;
    @JsonBackReference
    @OneToMany(targetEntity = Interest.class, mappedBy = "staff",fetch = FetchType.LAZY)
    private List<Interest> interestList;

    public Staff() {
    }

    public Staff(String jobNumber, String name, String telephone, String sex) {
        this.jobNumber = jobNumber;
        this.name = name;
        this.telephone = telephone;
        this.sex = sex;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<LearningPath> getLearningPathList() {
        return learningPathList;
    }

    public void setLearningPathList(List<LearningPath> learningPathList) {
        this.learningPathList = learningPathList;
    }


    public List<Interest> getInterestList() {
        return interestList;
    }

    public void setInterestList(List<Interest> interestList) {
        this.interestList = interestList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return id == staff.id &&
                sex == staff.sex &&
                Objects.equals(jobNumber, staff.jobNumber) &&
                Objects.equals(name, staff.name) &&
                Objects.equals(telephone, staff.telephone) &&
                Objects.equals(user, staff.user) &&
                Objects.equals(enterprise, staff.enterprise) &&
                Objects.equals(postType, staff.postType) &&
                Objects.equals(department, staff.department) &&
                Objects.equals(learningPathList, staff.learningPathList) &&
                Objects.equals(interestList, staff.interestList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, jobNumber, name, telephone, sex, user,enterprise, postType, department,learningPathList,interestList);
    }
}
