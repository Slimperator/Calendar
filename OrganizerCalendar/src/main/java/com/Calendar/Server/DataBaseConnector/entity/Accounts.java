package com.calendar.server.databaseconnector.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Владимир on 11.02.2017.
 */
@Entity
@Table(name = "accounts")
public class Accounts {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "account", unique = true, nullable = false)
    private String account;

    @Column(name = "passwordHash", nullable = false)
    private String passwordHash;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account_creator")
    private List<Calendar> calendar;

    public List<Calendar> getCalendar() {
        return calendar;
    }

    public void setCalendar(List<Calendar> calendar) {
        this.calendar = calendar;
    }

    public Accounts()
    {}

    public Accounts(String account, String passwordHash)
    {
        this.account = account;
        this.passwordHash = passwordHash;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
