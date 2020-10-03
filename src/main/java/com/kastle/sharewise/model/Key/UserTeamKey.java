package com.kastle.sharewise.model.Key;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Composite Primary Key for <code>UserKey</code> to connect the many-to-many relationship between
 * <code>User</code> and <code>Team</code>.
 * Will be embedded into <code>UserTeam</code>
 * @see com.kastle.sharewise.model.UserTeam
 * @see com.kastle.sharewise.model.User
 * @see com.kastle.sharewise.model.Team
 */
@Embeddable
public class UserTeamKey implements Serializable {
    /**
     * @return The <code>User</code>'s id
     */
    public long getUserId() {
        return userId;
    }

    /**
     * @param userId The <code>User</code>'s new id
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * @return The <code>Team</code>'s id
     */
    public long getTeamId() {
        return teamId;
    }

    /**
     * @param teamId  The <code>Team</code>'s new id
     */
    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    /**
     * @return The <code>String</code> representation of this instance
     */
    @Override
    public String toString() {
        return "UserTeamKey{" +
                "userId=" + userId +
                ", teamId=" + teamId +
                '}';
    }

    /**
     * @param o Another <code>UserTeamKey</code> instance to compare against this instance
     * @return <code>True</code> if the other instance is equal in value to this instance
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTeamKey that = (UserTeamKey) o;
        return userId == that.userId &&
                teamId == that.teamId;
    }

    /**
     * @return The hash of this instance
     */
    @Override
    public int hashCode() {
        return Objects.hash(userId, teamId);
    }

    /**
     * TODO What is this
     */
    @Column(name = "user_id")
    private long userId;
    /**
     * TODO What is this
     */
    @Column(name = "team_id")
    private long teamId;
}
