/**
 * Created by Владимир on 12.02.2017.
 */
import com.Calendar.Server.DataBaseConnector.entity.Accounts;
public class AccountUtil {
    public static Accounts createAccounts()
    {
        Accounts acc = new Accounts("azazaza","olololo" );
        return acc;
    }
}
