package edu.jxufe.tiamo.fishingpathsys.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "announcement",schema = "FishingPathSystem")
public class Announcement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "information")
    private String information;
    @Basic
    @Column(name = "time")
    private String time;

    @ManyToOne(targetEntity = Enterprise.class)
    @JoinColumn(name = "enterprise_id",referencedColumnName = "id")
    private Enterprise enterprise;
    @ManyToOne(targetEntity = Admin.class)
    @JoinColumn(name = "admin_id",referencedColumnName = "id")
    private Admin admin;
    @ManyToOne(targetEntity = Department.class)
    @JoinColumn(name = "department_id",referencedColumnName = "id")
    private Department department;

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Announcement that = (Announcement) o;
        return id == that.id &&
                title == that.title &&
                Objects.equals(information, that.information)&&
                Objects.equals(time, that.time)&&
                Objects.equals(enterprise,that.enterprise)&&
                Objects.equals(admin,that.admin)&&
                Objects.equals(department,that.department);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title,information,time,enterprise,admin,department);
    }
}
