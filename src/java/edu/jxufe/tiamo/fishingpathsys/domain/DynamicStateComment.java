package edu.jxufe.tiamo.fishingpathsys.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dynamic_state_comment", schema = "FishingPathSystem")
public class DynamicStateComment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    @Basic
    @Column(name = "content")
    private String content;

    @Basic
    @Column(name = "time")
    private String time;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id",referencedColumnName = "id",nullable = false)
    private User user;

    @JsonBackReference
    @ManyToOne(targetEntity = DynamicState.class)
    @JoinColumn(name = "dynamic_state_id",referencedColumnName = "id",nullable = false)
    private DynamicState dynamicState;


    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DynamicState getDynamicState() {
        return dynamicState;
    }

    public void setDynamicState(DynamicState dynamicState) {
        this.dynamicState = dynamicState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DynamicStateComment that = (DynamicStateComment) o;
        return id == that.id &&
                Objects.equals(content, that.content) &&
                Objects.equals(user, that.user) &&
                Objects.equals(dynamicState, that.dynamicState) &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, content, user, dynamicState, time);
    }
}
