package Test;

import Configuration.BrowserConfiguration;
import Pages.*;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

public class TestCase5 extends BrowserConfiguration{

    @Test
    public void execute() throws SQLException, IOException {
        Login login = new Login(page);
        login.verifyLogin("Test1235@gmail.com","Test@1234");
        Dashboard4 dashboard4 = new Dashboard4(page);
        dashboard4.verifyEventCreate("Tech Summit", "Tech Summit", "Workshop","Hyderabad", "Hyderabad");
    }
}
