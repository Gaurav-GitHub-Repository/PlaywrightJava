package Test;

import Configuration.BrowserConfiguration;
import Pages.Dashboard;
import Pages.Dashboard2;
import Pages.Login;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

public class TestCase3 extends BrowserConfiguration {

    @Test
    public void execute() throws SQLException, IOException {
        Login login = new Login(page);
        login.verifyLogin("Test1235@gmail.com","Test@1234");
        Dashboard dashboard = new Dashboard(page);
        dashboard.verifyFeaturedEvent();
        Dashboard2 dashboard2 = new Dashboard2(page);
        dashboard2.confirmBooking(4,"Pathange Gaurav","gaurav.pathange233334@gmail.com","8612344567");
    }

}
