package com.calendar.server.databaseconnector.repository;

import com.calendar.server.databaseconnector.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Владимир on 12.02.2017.
 */
public interface AccountsRepository extends JpaRepository<Accounts, Integer>{
    @Modifying
    @Transactional
    @Query("Delete from Accounts t where t.account= :accountName")
    Integer deleteByAccountHash(@Param("accountName")String accountName);

    @Query("select a from Accounts a where a.account = :accountName")
    Accounts findeByAccountName(@Param("accountName")String accountName);
}
