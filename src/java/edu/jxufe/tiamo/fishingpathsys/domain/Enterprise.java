package edu.jxufe.tiamo.fishingpathsys.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "enterprise",schema = "FishingPathSystem")
public class Enterprise {
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
    @Basic
    @Column(name = "link_man")
    private String linkMan;
    @Basic
    @Column(name = "telephone")
    private String telephone;

    @OneToOne(targetEntity = User.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "id",unique = true)
    private User user;
    @JsonBackReference
    @OneToMany(targetEntity = Announcement.class,mappedBy = "enterprise",fetch = FetchType.LAZY)
    private List<Announcement> announcementList;
    @ManyToOne(targetEntity = EnterpriseType.class)
    @JoinColumn(name = "enterprise_type_id", referencedColumnName = "id")
    private EnterpriseType enterpriseType;

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

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Announcement> getAnnouncementList() {
        return announcementList;
    }

    public void setAnnouncementList(List<Announcement> announcementList) {
        this.announcementList = announcementList;
    }

    public EnterpriseType getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(EnterpriseType enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enterprise that = (Enterprise) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(linkMan, that.linkMan) &&
                Objects.equals(telephone, that.telephone)&&
                Objects.equals(user,that.user) &&
                Objects.equals(announcementList,that.announcementList) &&
                Objects.equals(enterpriseType, that.enterpriseType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, linkMan, telephone, user, announcementList, enterpriseType);
    }
}
