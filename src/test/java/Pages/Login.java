package Pages;

import Configuration.DatabaseConfiguration;
import Configuration.PropertiesFile;
import UITest.LoginBase;
import Utility.ReusableComponents;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import java.io.IOException;


public class Login implements LoginBase{

    Page page;
    ReusableComponents component = new ReusableComponents(page);
    PropertiesFile propertiesFile = new PropertiesFile();

    DatabaseConfiguration dbConfiguration = new DatabaseConfiguration();

    //Assign page
    public Login(Page page) {
        this.page = page;
    }

    //========================Locator Value==========================
    String loginButton = "button#login-btn";

    //========================Implementation=========================

    @Override
    public void verifyUsername() throws IOException {
        String value = propertiesFile.readConfigProperties("Username");
        Locator Email = page.getByLabel("email");
        PlaywrightAssertions.assertThat(Email).isEditable();
        component.fillTextElement(Email, value);
        System.out.println("Email is entered");
    }

    @Override
    public void verifyPassword() throws IOException {
        String value = propertiesFile.readConfigProperties("Password");
        Locator Password = page.getByLabel("password");
        PlaywrightAssertions.assertThat(Password).isEditable();
        component.fillTextElement(Password, value);
        System.out.println("Password is entered");
    }

    @Override
    public void verifyButton() {
        Locator button = page.locator(loginButton);
        PlaywrightAssertions.assertThat(button).isVisible();
        component.clickElement(button);
        System.out.println("Clicked on sign in button");
    }

    @Override
    public void verifyLoginPage() throws IOException {
        verifyUsername();
        verifyPassword();
        verifyButton();
    }

    @Override
    public void verifyLogin(String emailValue, String passwordValue) {
        Locator Email = page.getByLabel("email");
        Locator Password = page.getByLabel("password");
        Locator button = page.locator(loginButton);

        if (Email.isEditable() && Password.isEditable()) {
            component.fillTextElement(Email, emailValue);
            System.out.println("Email is entered");
            component.fillTextElement(Password, passwordValue);
            System.out.println("Password is entered");
            if (button.isVisible()) {
                component.clickElement(button);
                System.out.println("Clicked on button");
            } else {
                System.out.println("Error occured at login button");
            }
        } else {
            System.out.println("Error occurred");
        }
    }


}
