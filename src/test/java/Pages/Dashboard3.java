package Pages;

import Configuration.PropertiesFile;
import UITest.CancelBookingBase;
import Utility.ReusableComponents;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import java.io.IOException;
import java.util.List;

public class Dashboard3 implements CancelBookingBase {

    Page page;
    ReusableComponents component = new ReusableComponents(page);
    PropertiesFile propertiesFile = new PropertiesFile();

    //Assign page
    public Dashboard3(Page page) {
        this.page = page;
    }

    //========================Locator Value==========================
    String myBookingsTab = "//*[contains(@class,'max-w-7xl mx-auto')]/div/div/a[3]";

    //========================Implementation=========================

    @Override
    public void verifyCancelBooking() throws IOException {
        String[] event = {"World Tech Summit"};

        Locator bookingsTab = page.locator(myBookingsTab);
        PlaywrightAssertions.assertThat(bookingsTab).isVisible();
        component.clickElement(bookingsTab);

        List<Locator> totalEvent = page.locator("//*[contains(@class,'space-y-4 mb-8')]/div").all();
        for (int i = 1; i <= totalEvent.size(); i++) {
            Locator eventName = page.locator("//*[contains(@class,'space-y-4 mb-8')]/div[" + i + "]/div[1]//div/h3");
            if (eventName.innerText().equalsIgnoreCase(event[0])) {
                System.out.println("Event found");
                Locator cancelBooking = page.locator("//*[contains(@class,'space-y-4 mb-8')]/div[" + i + "]/div[2]/button");
                if (cancelBooking.isVisible()) {
                    component.clickElement(cancelBooking);
                    System.out.println("Clicked on cancel booking button");
                    handleCancelBooking();
                    break;
                }
            }
        }
    }

    public void handleCancelBooking() {
        Locator popup = page.getByText("Yes, cancel it");
        component.clickElement(popup);
        System.out.println("Clicked on cancel booking pop up");
        Locator successMessage = page.getByText("Booking cancelled successfully");
        if(successMessage.isVisible())
        {
            System.out.println("Successfully canceled the event");
        }
        else
        {
            System.out.println("Event is not cancelled");
        }
    }

}
