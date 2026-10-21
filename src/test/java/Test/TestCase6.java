package Test;

import Configuration.BrowserConfiguration;
import Pages.Dashboard4;
import Pages.Logout;
import Pages.Login;
import Utility.ReusableComponents;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class TestCase6 extends BrowserConfiguration {

    ReusableComponents component = new ReusableComponents(page);

    @Test
    public void execute() throws SQLException, IOException {

        String[] var = component.readExcel("TestCase", 18, 4);

        while (var[0] != null) {

            String[] value = var[0].split(",");
            List<String> totalKeywords = Arrays.asList(value);
            System.out.println("Total keywords :" + totalKeywords.size());

            for (int i = 0; i <= totalKeywords.size() - 1; i++) {
                if (value[i].equalsIgnoreCase("Login")) {
                    Login login = new Login(page);
                    login.verifyLogin("Test1235@gmail.com", "Test@1234");
                } else if (value[i].equalsIgnoreCase("Logout")) {
                    Logout logout = new Logout(page);
                    logout.verifyLogout();
                } else {
                    System.out.println("Error Occurred");
                }
            }
            break;
        }

    }

}
