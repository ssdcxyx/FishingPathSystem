package edu.jxufe.tiamo.fishingpathsys.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user",schema = "FishingPathSystem")
@JsonIgnoreProperties(value={"staff","enterprise","admin"})
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @Basic
    @Column(name = "account")
    private String account;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "role")
    private short role;
    @Basic
    @Column(name = "logo_path")
    private String logoPath;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "telephone")
    private String telephone;
    @Basic
    @Column(name = "sex")
    private String sex;
    @Basic
    @Column(name = "description")
    private String description;


    @JsonBackReference
    @OneToMany(targetEntity = DynamicState.class, mappedBy = "user",fetch = FetchType.LAZY)
    private List<DynamicState> dynamicStateList;

    @JsonBackReference
    @OneToMany(targetEntity = DynamicStateLike.class, mappedBy = "user",fetch = FetchType.LAZY)
    private List<DynamicStateLike> dynamicStateLikeList;

    @JsonBackReference
    @OneToMany(targetEntity = DynamicStateComment.class, mappedBy = "user",fetch = FetchType.LAZY)
    private List<DynamicStateComment> dynamicStateCommentList;

    @JsonBackReference
    @OneToOne(targetEntity = Staff.class,mappedBy = "user",fetch = FetchType.LAZY)
    private Staff staff;

    @JsonBackReference
    @OneToOne(targetEntity = Enterprise.class,mappedBy = "user",fetch = FetchType.LAZY)
    private Enterprise enterprise;

    @JsonBackReference
    @OneToOne(targetEntity = Admin.class,mappedBy = "user",fetch = FetchType.LAZY)
    private Admin admin;

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public short getRole() {
        return role;
    }

    public void setRole(short role) {
        this.role = role;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public List<DynamicState> getDynamicStateList() {
        return dynamicStateList;
    }

    public void setDynamicStateList(List<DynamicState> dynamicStateList) {
        this.dynamicStateList = dynamicStateList;
    }

    public List<DynamicStateLike> getDynamicStateLikeList() {
        return dynamicStateLikeList;
    }

    public void setDynamicStateLikeList(List<DynamicStateLike> dynamicStateLikeList) {
        this.dynamicStateLikeList = dynamicStateLikeList;
    }

    public List<DynamicStateComment> getDynamicStateCommentList() {
        return dynamicStateCommentList;
    }

    public void setDynamicStateCommentList(List<DynamicStateComment> dynamicStateCommentList) {
        this.dynamicStateCommentList = dynamicStateCommentList;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(account, user.account) &&
                Objects.equals(password, user.password) &&
                Objects.equals(role,user.role) &&
                Objects.equals(logoPath,user.logoPath) &&
                Objects.equals(name, user.name) &&
                Objects.equals(telephone, user.telephone) &&
                Objects.equals(sex,user.sex) &&
                Objects.equals(description,user.description) &&
                Objects.equals(dynamicStateList,user.dynamicStateList) &&
                Objects.equals(dynamicStateLikeList,user.dynamicStateLikeList) &&
                Objects.equals(dynamicStateCommentList,user.dynamicStateCommentList) &&
                Objects.equals(admin, user.admin) &&
                Objects.equals(enterprise, user.enterprise) &&
                Objects.equals(staff, user.staff);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, account, password, role, logoPath, name, telephone, sex, description, dynamicStateList, dynamicStateLikeList, dynamicStateLikeList, admin, enterprise, staff);
    }
}
