package edu.jxufe.tiamo.fishingpathsys.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dynamic_state_like", schema = "FishingPathSystem")
public class DynamicStateLike {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DynamicStateLike that = (DynamicStateLike) o;
        return id == that.id &&
                Objects.equals(user, that.user) &&
                Objects.equals(time, that.time) &&
                Objects.equals(dynamicState, that.dynamicState);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, user, time, dynamicState);
    }
}
