package com.kastle.sharewise.model;

import com.kastle.sharewise.model.Key.UserTeamKey;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;
import java.util.Objects;

/**
 * Will convert to a Many-To-Many SQL table, with extra columns, between User and Table instances
 * Will be automatically made into a SQL table though SpringBoot as specified to in application.yml.
 */
@Entity
public class UserTeam implements Serializable {
    /**
     * The Many-To-Many primary key
     */
    @EmbeddedId
    private UserTeamKey id;

    /**
     * TODO What is this
     */
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * TODO What is this
     */
    @ManyToOne
    @MapsId("teamId")
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    /**
     * @return The <code>User</code>> connected to this instance
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user The new <code>User</code> connected to this instance
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return This instance as a <code>String</code>
     */
    @Override
    public String toString() {
        return "UserTeam{" +
                "id=" + id +
                ", user=" + user +
                ", team=" + team +
                '}';
    }

    /**
     * @param o Another <code>UserTeam</code> instance to compare to
     * @return true if the other <code>UserTeam</code> is equal to this instance by value
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTeam userTeam = (UserTeam) o;
        return id.equals(userTeam.id) &&
                user.equals(userTeam.user) &&
                team.equals(userTeam.team);
    }

    /**
     * @return The instance's hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, user, team);
    }

    /**
     * @return The Team connected to this instance
     */
    public Team getTeam() {
        return team;
    }

    /**
     * @param team  The new Team connected to this instance
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * @return The Key of this instance
     */
    public UserTeamKey getId() {
        return id;
    }

    /**
     * @param id  The new Key of this instance
     */
    public void setId(UserTeamKey id) {
        this.id = id;
    }
}
