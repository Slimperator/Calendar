package com.calendar.server.databaseconnector.service.impl;

import com.calendar.server.databaseconnector.entity.Accounts;
import com.calendar.server.databaseconnector.entity.Calendar;
import com.calendar.server.databaseconnector.entity.Invites;
import com.calendar.server.databaseconnector.repository.InvitesRepository;
import com.calendar.server.databaseconnector.service.InvitesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Владимир on 11.04.2017.
 */
@Service
public class InvitesServiceImpl implements InvitesService{
    @Autowired
    InvitesRepository invitesRepository;

    @Override
    public Invites addNewInvites(Invites invite) {
        return invitesRepository.saveAndFlush(invite);
    }

    @Override
    public List<Accounts> getAllInvites(Calendar event) {
        List<Invites> list = invitesRepository.findeByEvent(event);
        List<Accounts> result = new ArrayList<>();
        for(Invites invites: list)
            result.add(invites.getAccount_invited());
        return result;
    }

    @Override
    public Invites getInvite(Accounts account, Calendar event) {
        return invitesRepository.findeInvite(event, account);
    }

    @Override
    public void deleteInvite(Invites invite) {
        invitesRepository.delete(invite);
    }
}
