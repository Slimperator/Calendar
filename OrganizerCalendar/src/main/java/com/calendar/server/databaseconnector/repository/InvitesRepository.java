package com.calendar.server.databaseconnector.repository;

import com.calendar.server.databaseconnector.entity.Accounts;
import com.calendar.server.databaseconnector.entity.Calendar;
import com.calendar.server.databaseconnector.entity.Invites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Владимир on 11.04.2017.
 */
public interface InvitesRepository extends JpaRepository<Invites, Integer> {
    @Query("select a from Invites a where a.event_id = :event")
    List<Invites> findeByEvent(@Param("event")Calendar event);

    @Query("select a from Invites a where a.event_id = :event and a.account_invited = :account")
    Invites findeInvite(@Param("event")Calendar event, @Param("account")Accounts account);
}
