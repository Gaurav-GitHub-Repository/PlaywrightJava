package Pages;

import Configuration.PropertiesFile;
import UITest.DashboardBase;
import Utility.ReusableComponents;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import java.io.IOException;
import java.util.List;

public class Dashboard implements DashboardBase {

    Page page;
    ReusableComponents component = new ReusableComponents(page);
    PropertiesFile propertiesFile = new PropertiesFile();

    //Assign page
    public Dashboard(Page page) {
        this.page = page;
    }

    //========================Locator Value==========================
    String bookNow = "//*[contains(text(),'World Tech Summit')]/parent::a/parent::div/div[2]/a";
    String event = "//div[contains(@class,'grid grid-cols-1')]/child::*[3]/div[2]/a[@href='/events/283']";
    //========================Implementation=========================

    @Override
    public void verifyEvent() throws IOException {
        Locator event = page.getByText("World Tech Summit");
        component.scrollToElement(event);
        PlaywrightAssertions.assertThat(event).isVisible();
        System.out.println("Event found");
        verifyBookNowButton();
    }

    @Override
    public void verifyBookNowButton() throws IOException {
        Locator bookNowbutton = page.locator(bookNow);
        component.scrollToElement(bookNowbutton);
        PlaywrightAssertions.assertThat(bookNowbutton).isVisible();
        component.clickElement(bookNowbutton);
        System.out.println("Clicked on Book Now button");
    }

    public void verifyFeaturedEvent() {
        Locator event = page.locator("//div[contains(@class,'grid grid-cols-1')]/child::*[3]/div[2]/a[@href='/events/283']");
        component.scrollToElement(event);
        if (event.innerText().equalsIgnoreCase("World Tech Summit")) {
            System.out.println("Event found");
            Locator bookNowButton = page.locator("//*[contains(text(),'World Tech Summit')]/parent::a/parent::div/div[2]/a");
            component.scrollToElement(bookNowButton);
            if (bookNowButton.isVisible()) {
                component.clickElement(bookNowButton);
                System.out.println("Clicked on Book Now button");
            } else {
                System.out.println("Error occured at book now button");
            }
        } else {
            System.out.println("Event not found");
        }
    }

    public void bookEvent() {
        List<Locator> totalEvent = page.locator("//div[contains(@class,'grid grid-cols-1')]/child::*/div/img").all();
        for (int i = 1; i <= totalEvent.size(); i++) {
            Locator event = page.locator("//div[contains(@class,'grid grid-cols-1')]/child::*[" + i + "]/div[2]/a");
            if (event.innerText().equalsIgnoreCase("World Tech Summit")) {
                System.out.println("Event is verified");
                Locator bookNow = page.locator("//div[contains(@class,'grid grid-cols-1')]/child::*[" + i + "]/div[2]/div[2]/a");
                if (bookNow.isVisible()) {
                    bookNow.waitFor();
                    bookNow.click();
                    System.out.println("Clicked on Book Now button");
                }
            }
        }
    }

}
