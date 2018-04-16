package edu.jxufe.tiamo.fishingpathsys.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "announcement",schema = "FishingPathSystem")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "@id")
public class Announcement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @Basic
    @Column(name = "title")
    private short title;
    @Basic
    @Column(name = "information")
    private String information;

    @ManyToOne(targetEntity = Enterprise.class)
    @JoinColumn(name = "enterprise_id",referencedColumnName = "id")
    @JsonManagedReference
    private Enterprise enterprise;
    @ManyToOne(targetEntity = Admin.class)
    @JoinColumn(name = "admin_id",referencedColumnName = "id")
    private Admin admin;

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }


    public short getTitle() {
        return title;
    }

    public void setTitle(short title) {
        this.title = title;
    }


    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Announcement that = (Announcement) o;
        return id == that.id &&
                title == that.title &&
                Objects.equals(information, that.information)&&
                Objects.equals(enterprise,that.enterprise)&&
                Objects.equals(admin,that.admin);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, information,enterprise,admin);
    }
}
