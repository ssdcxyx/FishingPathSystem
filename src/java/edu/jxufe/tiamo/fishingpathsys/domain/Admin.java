package edu.jxufe.tiamo.fishingpathsys.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id",referencedColumnName = "id",unique = true)
    private User user;
    @JsonBackReference
    @OneToMany(targetEntity = Announcement.class,mappedBy = "admin",fetch = FetchType.LAZY)
    private List<Announcement> announcementList;


    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
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
                Objects.equals(user,admin.user) &&
                Objects.equals(announcementList,admin.announcementList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id,user,announcementList);
    }
}
