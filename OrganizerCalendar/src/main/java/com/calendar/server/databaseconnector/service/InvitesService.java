package com.calendar.server.databaseconnector.service;

import com.calendar.server.databaseconnector.entity.Accounts;
import com.calendar.server.databaseconnector.entity.Calendar;
import com.calendar.server.databaseconnector.entity.Invites;

import java.util.List;

/**
 * Created by Владимир on 11.04.2017.
 */
public interface InvitesService {
    List<Accounts> getAllInvites(Calendar event);
    List<Calendar> getAllInvitesForThisAccount(Accounts account);
    Invites addNewInvites(Invites invite);
    Invites getInvite(Accounts account, Calendar event);
    void deleteInvite(Invites invite);
}
