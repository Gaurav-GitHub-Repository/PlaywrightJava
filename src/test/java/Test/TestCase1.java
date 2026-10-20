package Test;

import Configuration.BrowserConfiguration;
import Pages.Login;
import com.microsoft.playwright.Page;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

public class TestCase1 extends BrowserConfiguration {

    @Test
    public void execute() {
        Login login = new Login(page);
        //login.verifyLoginPage();
        login.verifyLogin("Test1235@gmail.com","Test@1234");
    }
}
