package Pages;

import Configuration.PropertiesFile;
import UITest.LogoutBase;
import Utility.ReusableComponents;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.io.IOException;

public class Logout implements LogoutBase {

    Page page;
    ReusableComponents component = new ReusableComponents(page);
    PropertiesFile propertiesFile = new PropertiesFile();

    //Assign page
    public Logout(Page page) {
        this.page = page;
    }

    //========================Locator Value==========================
    String logout = "//div[contains(@class,'max-w-7xl')]/div/div/div[2]/button";

    //========================Implementation=========================

    @Override
    public void verifyLogout() throws IOException {
        Locator logoutButton = page.locator(logout);
        if(logoutButton.isVisible())
        {
            component.clickElement(logoutButton);
            System.out.println("Clicked on logout button");
        }
        else
        {
            System.out.println("Error occured while logout");
        }
    }
}
