package com.calendar.server.databaseconnector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.calendar.server.databaseconnector.entity.Calendar;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.sql.Date;
/**
 * Created by Владимир on 12.02.2017.
 */
public interface CalendarRepository extends JpaRepository<Calendar, Integer>{
    @Query("select a from Calendar a where a.name = :name")
    Calendar findeByEventName(@Param("name")String eventName);

    @Query("select a from Calendar a where a.account_creator =:account and a.begin_data between :begin and :endd")
    List<Calendar> findeByDateRange(@Param("begin") Date begin,
                                    @Param("endd") Date end,
                                    @Param("account") String account);
    @Modifying
    @Transactional
    @Query("Delete from Calendar t where t.account_creator= :accountName and t.name = :name")
    Integer deleteEvent(@Param("name")String eventName, @Param("accountName")String accountName);
    //!!! Возможно стоит добавить проверку аккаунта до обращения к БД
}
