package com.kastle.sharewise.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * A class for storing User data
 * Will be automatically made into a SQL table though SpringBoot as specified to in application.yml.
 * //TODO make secure with Spring Security
 */
@Entity
public class User implements Serializable {

    /**
     * BIGINT id PRIMARY KEY NOT NULL AUTO_INCREMENT
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;
    /**
     * VARCHAR(32) username NOT NULL
     */
    @Column(name = "username", nullable = false, length = 32)
    private String username;
    /**
     * VARCHAR(32) email
     */
    @Column(name = "email", length = 32)
    private String email;
    /**
     * TODO replace with Spring Security
     * VARCHAR(64) password NOT NULL
     */
    @Column(name = "password", nullable = false, length = 64)
    private String password;
    /**
     * DATETIME date_created
     */
    @JsonSerialize(using = DateSerializer.class)
    @Column(name = "date_created")
    private Date created;
    /**
     * DATETIME last_login
     */
    @JsonSerialize(using = DateSerializer.class)
    @Column(name = "last_login")
    private Date lastLogin;
    /**
     * VARCHAR(16) role NOT NULL
     */
    @Column(name = "role", nullable = false, length = 16)
    private String role;

    /**
     * TODO What is this
     */
    @OneToMany(mappedBy = "user")
    private Set<UserTeam> userTeams;
    /**
     * TODO What is this
     */
    @OneToMany(mappedBy = "sender")
    private Set<Transaction> senderTransactions;
    /**
     * TODO What is this
     */
    @OneToMany(mappedBy = "receiver")
    private Set<Transaction> receiverTransactions;

    /**
     * @return The user id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id The new user id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return The user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username The user's new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return The user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email The user's new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Note: Will be removed with something better when adding Spring Security
     * @return The user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Note: Will be removed with something better when adding Spring Security
     * @param password The user's new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return The timestamp of when the user created their account
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created The new timestamp saying when the user created their account
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return The timestamp of when the user last logged in
     */
    public Date getLastLogin() {
        return lastLogin;
    }

    /**
     * @param lastLogin The updated timestamp of when the user last logged in
     */
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     * @return The set of UserTeams this User is connected to
     */
    public Set<UserTeam> getUserTeams() {
        return userTeams;
    }

    /**
     * @param userTeams The new set of UserTeams this user is connected to
     */
    public void setUserTeams(Set<UserTeam> userTeams) {
        this.userTeams = userTeams;
    }

    /**
     * @return The full user's data with private info replaced with asterisks
     */
    public User abstruse() {
        setPassword("********");
        setEmail(email.substring(2).concat("******"));
        return this;
    }

    /**
     * @return This class instance as String
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", created=" + created +
                ", lastLogin=" + lastLogin +
                ", role='" + role + '\'' +
                ", userTeams=" + userTeams +
                ", senderTransactions=" + senderTransactions +
                ", receiverTransactions=" + receiverTransactions +
                '}';
    }

    /**
     * @param o Another User instance
     * @return true if the other instance is equal in value to this instance
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                username.equals(user.username) &&
                email.equals(user.email) &&
                password.equals(user.password) &&
                created.equals(user.created) &&
                Objects.equals(lastLogin, user.lastLogin) &&
                role.equals(user.role) &&
                userTeams.equals(user.userTeams) &&
                senderTransactions.equals(user.senderTransactions) &&
                receiverTransactions.equals(user.receiverTransactions);
    }

    /**
     * @return The hash of this User instance
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, created, lastLogin, role, userTeams, senderTransactions, receiverTransactions);
    }

    /**
     * @return The user's role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role The User's new role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return The set of all Transactions this user is connected to as the sender
     */
    public Set<Transaction> getSenderTransactions() {
        return senderTransactions;
    }

    /**
     * @param senderTransactions The new set of all Transactions this user is connected to as the sender
     */
    public void setSenderTransactions(Set<Transaction> senderTransactions) {
        this.senderTransactions = senderTransactions;
    }

    /**
     * @return The set of all Transactions this user is connected to as the receiver
     */
    public Set<Transaction> getReceiverTransactions() {
        return receiverTransactions;
    }

    /**
     * @param receiverTransactions The new set of all Transactions this user is connected to as the receiver
     */
    public void setReceiverTransactions(Set<Transaction> receiverTransactions) {
        this.receiverTransactions = receiverTransactions;
    }
}
