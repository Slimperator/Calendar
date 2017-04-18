package com.calendar.server.databaseconnector.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Date;

import static javax.persistence.CascadeType.*;

/**
 * Created by Владимир on 11.04.2017.
 */
@Entity
@Table(name = "invites")
public class Invites {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @ManyToOne(cascade = {MERGE, REMOVE, REFRESH, DETACH})
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "event_id", nullable = false)
    private Calendar event_id;

    @ManyToOne(cascade = {MERGE, REMOVE, REFRESH, DETACH})
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "account_invited", nullable = false)
    private Accounts account_invited;

    public Invites() {
    }

    public Invites(Accounts account, Calendar event)
    {
        event_id=event;
        account_invited = account;
    }

    public Calendar getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Calendar event_id) {
        this.event_id = event_id;
    }

    public Accounts getAccount_invited() {
        return account_invited;
    }

    public void setAccount_invited(Accounts account_invited) {
        this.account_invited = account_invited;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}