package Test;

import Configuration.BrowserConfiguration;
import Pages.Logout;
import Pages.Login;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

public class TestCase6 extends BrowserConfiguration {

    @Test
    public void execute() throws SQLException, IOException {
        Login login = new Login(page);
        login.verifyLogin("Test1235@gmail.com","Test@1234");
        Logout dashboard5 = new Logout(page);
        dashboard5.verifyLogout();
    }

}
