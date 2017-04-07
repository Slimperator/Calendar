/**
 * Created by Владимир on 12.02.2017.
 */
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.calendar.server.databaseconnector.entity.Accounts;
import com.calendar.server.databaseconnector.service.AccountsService;
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

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
    }

    @Test
    public void testSaveBank() throws Exception {
        accountsService.addAccount(AccountUtil.createAccounts());

        Accounts accounts = accountsService.getAccount("asdas");
        if(accounts!=null)
            System.out.println("account get!");
        else
            System.out.println("account null!");
        accountsService.deleteAccount("asdas");
        System.out.println("5d41402abc4b2a76b9719d911017c592");
        System.out.println(Tools.MD5Generator("hello"));
    }
}
