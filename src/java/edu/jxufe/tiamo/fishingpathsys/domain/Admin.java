package edu.jxufe.tiamo.fishingpathsys.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "admin",schema = "FishingPathSystem")
public class Admin {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "contact")
    private String contact;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id",referencedColumnName = "id",unique = true)
    private User user;
    @OneToMany(targetEntity = Announcement.class,mappedBy = "admin",fetch = FetchType.LAZY)
    private List<Announcement> announcementList;


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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return id == admin.id &&
                Objects.equals(name, admin.name) &&
                Objects.equals(contact, admin.contact)&&
                Objects.equals(user,admin.user) &&
                Objects.equals(announcementList,admin.announcementList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, contact,user,announcementList);
    }
}
