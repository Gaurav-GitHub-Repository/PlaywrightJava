package Pages;

import Configuration.PropertiesFile;
import UITest.NewEventBase;
import Utility.ReusableComponents;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

public class Dashboard4 implements NewEventBase {

    Page page;
    ReusableComponents component = new ReusableComponents(page);
    PropertiesFile propertiesFile = new PropertiesFile();

    //Assign page
    public Dashboard4(Page page) {
        this.page = page;
    }

    //========================Locator Value==========================
    String admin = "//div[contains(@class,'max-w-7xl')]/div/div/div/button[contains(text(),'Admin')]";
    String manageEvents = "//a[@href='/admin/events']/parent::div/a[1]";
    String title = "input#event-title-input";
    String category = "select#category";
    String city = "input#city";
    String venue = "input#venue";
    String price = "[id='price-($)']";
    String seats = "#total-seats";
    String date = "[id='event-date-&-time']";
    String home = "//div[contains(@class,'max-w-7xl')]/div[1]/div[1]/a[1]";
    //========================Implementation=========================

    @Override
    public void verifyEventCreate(String fillTitle, String fillDescription, String selectCategory, String fillCity, String fillVenue) throws IOException {
        clickOnEvent();
        Locator enterTitle = page.locator(title);
        if(enterTitle.isEditable())
        {
            component.clearTextElement(enterTitle);
            component.fillTextElement(enterTitle, fillTitle);
            Locator description = page.getByPlaceholder("Describe the event…");
            if(description.isEditable())
            {
                component.clearTextElement(description);
                component.fillTextElement(description, fillDescription);
            }
        }
        component.selectDropdown(page.locator(category), selectCategory);
        Locator enterCity = page.locator(city);
        if(enterCity.isEditable())
        {
            component.clearTextElement(enterCity);
            component.fillTextElement(enterCity, fillCity);
            Locator enterVenue = page.locator(venue);
            if(enterVenue.isEditable())
            {
                component.clearTextElement(enterVenue);
                component.fillTextElement(enterVenue, fillVenue);
            }
        }
        //getDate(0);
        Locator enterDate = page.getByLabel("Event Date & Time");
        component.fillTextElement(enterDate, "2026-11-15T18:30");
        Locator enterPrice = page.getByLabel("Price ($)");
        component.fillTextElement(enterPrice, "499.99");
        Locator enterTotalSeats = page.getByLabel("Total Seats");
        component.fillTextElement(enterPrice, "500");
        page.getByLabel("Total Seats").fill("500");
        Locator addButton = page.locator("//form[@id='admin-event-form']/div/button");
        component.scrollToElement(addButton);
        if(addButton.isVisible())
        {
            component.clickElement(addButton);
        }
        verifyDashboard();
    }

    public void clickOnEvent() {
        component.clickElement(page.locator(admin));
        component.hoverOverElement(page.locator(manageEvents));
        PlaywrightAssertions.assertThat(page.locator(manageEvents)).isVisible();
        component.clickElement(page.locator(manageEvents));
    }

    public void verifyDashboard() {
        PlaywrightAssertions.assertThat(page.locator(home)).isVisible();
        component.clickElement(page.locator(home));

        List<Locator> totalEvent = page.locator("//div[contains(@class,'grid grid-cols-1')]/child::*/div[1]").all();
        for (int i = 1; i <= totalEvent.size(); i++) {
            Locator event = page.locator("//div[contains(@class,'grid grid-cols-1')]/child::*[" + i + "]/div[2]/a");
            if (event.innerText().equalsIgnoreCase("Tech Summit")) {
                System.out.println("Event is verified in the dashboard page");
                break;
            }
        }
    }

}
