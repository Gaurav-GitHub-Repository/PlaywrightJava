package Test;

import Configuration.BrowserConfiguration;
import Pages.Dashboard;
import Pages.Login;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

public class TestCase2 extends BrowserConfiguration {

    @Test
    public void execute() throws SQLException, IOException {
        Login login = new Login(page);
        login.verifyLogin("Test1235@gmail.com","Test@1234");
        Dashboard dashboard = new Dashboard(page);
        //dashboard.verifyEvent();
        //dashboard.verifyFeaturedEvent();
        dashboard.bookEvent();
    }

}
