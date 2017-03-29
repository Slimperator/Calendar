package com.calendar.server.databaseconnector.repository;

import com.calendar.server.databaseconnector.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Владимир on 12.02.2017.
 */
public interface AccountsRepository extends JpaRepository<Accounts, Integer>{
    @Query("Delete from Accounts t where t.accountHash= :accountHash")
    Long deleteByAccountHash(@Param("accountHash")String accountHash);

    @Query("select a from Accounts a where a.accountHash = :accountHash")
    Accounts findeByAccountHash(@Param("accountHash")String accountHash);
}
