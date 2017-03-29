package com.calendar.server.databaseconnector.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Владимир on 11.02.2017.
 */
@Entity
@Table(name = "calendar")
public class Calendar {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", nullable = false)
    private Accounts account_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "begin_data", nullable = false)
    private Date begin_data;

    @Column(name = "end_data", nullable = false)
    private Date end_data;

    public Calendar(String name, String description, Date begin_data, Date end_data, Accounts account_id)
    {
        this.name = name;
        this.description = description;
        this.begin_data = begin_data;
        this.end_data = end_data;
        this.account_id = account_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Accounts getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Accounts account_id) {
        this.account_id = account_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getBegin_data() {
        return begin_data;
    }

    public void setBegin_data(Date begin_data) {
        this.begin_data = begin_data;
    }

    public Date getEnd_data() {
        return end_data;
    }

    public void setEnd_data(Date end_data) {
        this.end_data = end_data;
    }
}
