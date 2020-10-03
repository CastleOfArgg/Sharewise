package com.kastle.sharewise.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * A class for groups (variable number) of users to be added to.
 * Will be automatically made into a SQL table though SpringBoot as specified to in application.yml.
 */
@Entity
public class Team implements Serializable {
    /**
     * BIGINT id PRIMARY KEY NOT NULL AUTO_INCREMENT
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;
    /**
     * VARCHAR(32) name NOT NULL
     */
    @Column(name = "name", nullable = false, length = 32)
    private String name;
    /**
     * DATETIME date_created
     */
    @Column(name = "date_created")
    private Date created;
    /**
     * DATETIME last_use
     */
    @Column(name = "last_use")
    private Date lastUsed;
    /**
     * TODO what is this
     */
    @OneToMany(mappedBy = "id.team")
    private Set<Transaction> transaction;
    /**
     * TODO what is this
     */
    @OneToMany(mappedBy = "team")
    private Set<UserTeam> userTeams;

    /**
     * @return This instance as a String
     */
    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", created=" + created +
                ", lastUsed=" + lastUsed +
                ", userTeams=" + userTeams +
                '}';
    }

    /**
     * @return The instance id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id The new instance id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @param o Another instance of this class
     * @return true if the other instance is equal in value to this instance
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id &&
                name.equals(team.name) &&
                created.equals(team.created) &&
                lastUsed.equals(team.lastUsed) &&
                userTeams.equals(team.userTeams);
    }

    /**
     * @return A hash value of this instance
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, created, lastUsed, userTeams);
    }

    /**
     * @return The name of the Team
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The new name of the team
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The timestamp of instance creation
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created The new timestampt of when this Team was created
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return The timestamp of instance's last update/creation
     */
    public Date getLastUsed() {
        return lastUsed;
    }

    /**
     * @param lastUsed The timestamp for when this Team was last updated
     */
    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }

    /**
     * @return The set of UserTeams this Team is connected to
     */
    public Set<UserTeam> getUserTeams() {
        return userTeams;
    }

    /**
     * @param userTeams The new set of UserTeams that this Team is connected to
     */
    public void setUserTeams(Set<UserTeam> userTeams) {
        this.userTeams = userTeams;
    }
}
