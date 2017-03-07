package com.Calendar.Server.DataBaseConnector.entity;

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

    @Column(name = "accountHash", unique = true, nullable = false)
    private String accountHash;

    @Column(name = "passwordHash", nullable = false)
    private String passwordHash;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account_id")
    private List<Calendar> calendar;

    public List<Calendar> getCalendar() {
        return calendar;
    }

    public void setCalendar(List<Calendar> calendar) {
        this.calendar = calendar;
    }

    public Accounts(String accountHash, String passwordHash)
    {
        this.accountHash = accountHash;
        this.passwordHash = passwordHash;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountHash() {
        return accountHash;
    }

    public void setAccountHash(String accountHash) {
        this.accountHash = accountHash;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
