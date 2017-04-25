/**
 * Created by Владимир on 12.02.2017.
 */
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.calendar.server.databaseconnector.entity.Accounts;
import com.calendar.server.databaseconnector.entity.Calendar;
import com.calendar.server.databaseconnector.service.AccountsService;
import com.calendar.server.databaseconnector.service.CalendarService;
import com.calendar.server.security.Tools;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.calendar.server.databaseconnector.config.DataBaseConfig;

import java.sql.Date;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseConfig.class)
@WebAppConfiguration
public class AccountsDBTest {

    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private CalendarService calendarService;

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
    }

    @Test
    public void testSaveBank() throws Exception {
        //accountsService.addAccount(AccountUtil.createAccounts());

        Accounts accounts = accountsService.getAccount("hello");
        if(accounts!=null)
            System.out.println("account get!");
        else
            System.out.println("account null!");

        java.util.Date datetime = new java.util.Date();
        Date date = new Date(datetime.getTime());

        Calendar calendar = new Calendar();
        calendar.setName("test");
        calendar.setDescription("test");
        calendar.setBegin_data(date);
        System.out.println(date.toString());
        calendar.setEnd_data(date);
        calendar.setAccount_creator(accounts);
        accounts.getCalendar().add(calendar);
        accountsService.editAccount(accounts);
        System.out.println("Edit Done!");
        calendarService.addEvent(calendar);

        //accountsService.deleteAccount("asdas");
        //System.out.println("5d41402abc4b2a76b9719d911017c592");
        //System.out.println(Tools.MD5Generator("hello"));
    }
}
