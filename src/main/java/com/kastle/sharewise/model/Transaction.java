package com.kastle.sharewise.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

/**
 * A class for storing Users' transaction data
 * Will be automatically made into a SQL table though SpringBoot as specified to in application.yml.
 * @see User
 * @see Team
 */
//TODO FIX
@Entity
public class Transaction implements Serializable {
    /**
     * A Composite Primary Key for Transaction instances.
     * Includes the Team' primary key: team_id, and the User's primary key: id
     */
    @Embeddable
    public class TransactionKey implements Serializable {
        /**
         * TODO What is this
         */
        @ManyToOne
        @JoinColumn(name = "team_id", nullable = false)
        private Team team;

        /**
         * TODO What is this
         */
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id", nullable = false)
        private long id;
    }

    /**
     * @return The Composite Primary key of this instance
     */
    public TransactionKey getId() {
        return id;
    }

    /**
     * @param id The new Composite Primary key of this instance
     */
    public void setId(TransactionKey id) {
        this.id = id;
    }

    /**
     * @return The User connected to this instance as the Sender
     */
    public User getSender() {
        return sender;
    }

    /**
     * @return The User connected to this instance as the Sender
     */
    public void setSender(User sender) {
        this.sender = sender;
    }

    /**
     * @return The User connected to this instance as the receiver
     */
    public User getReceiver() {
        return receiver;
    }

    /**
     * @return The User connected to this instance as the receiver
     */
    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    /**
     * @return The amount of money sent in the transaction
     */
    public float getAmount() {
        return amount;
    }

    /**
     * @param amount The new amount of money sent in the transaction
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     * The Composite Primary Key
     * @see TransactionKey
     */
    @EmbeddedId
    private TransactionKey id;
    /**
     * TODO What is this
     */
    @ManyToOne
    @JoinColumn(name = "user_sender_id", nullable = false)
    private User sender;
    /**
     * TODO What is this
     */
    @ManyToOne
    @JoinColumn(name = "user_receiver_id", nullable = false)
    private User receiver;
    /**
     * FLOAT amount NOT NULL
     */
    @Column(name = "amount", nullable = false)
    private float amount;

    /**
     * @return This instance as a String
     */
    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", amount=" + amount +
                '}';
    }

    /**
     * @param o Another Transaction to compare against
     * @return true if other Transaction instance is equal to this by value
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Float.compare(that.amount, amount) == 0 &&
                id.equals(that.id) &&
                sender.equals(that.sender) &&
                receiver.equals(that.receiver);
    }

    /**
     * @return This instance's hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, sender, receiver, amount);
    }

}
