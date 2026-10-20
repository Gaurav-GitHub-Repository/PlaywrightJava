package Pages;

import Configuration.PropertiesFile;
import UITest.ConfirmedBookingBase;
import Utility.ReusableComponents;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import java.io.IOException;

public class Dashboard2 implements ConfirmedBookingBase {

    Page page;
    ReusableComponents component = new ReusableComponents(page);
    PropertiesFile propertiesFile = new PropertiesFile();

    //Assign page
    public Dashboard2(Page page) {
        this.page = page;
    }

    //========================Locator Value==========================
    String fullName = "input#customerName";
    String email = "input#customer-email";
    String phoneNumber = "input#phone";
    String confirmBookingButton = "button#confirm-booking";
    String booking = "//*[contains(text(),'Booking Confirmed!')]";
    //========================Implementation=========================

    @Override
    public void verifyTickets(int numberOfTickets) throws IOException {
        int maxTickets = 8;
        for (int i = 1; i <= maxTickets; i++) {
            Locator selectedTickets = page.locator("//form[contains(@class,'space-y-4')]/div[1]/div/span[1]");
            String value = String.valueOf(numberOfTickets);
            if (selectedTickets.innerText().equalsIgnoreCase(value)) {
                System.out.println("Number of Tickets Selected : " + numberOfTickets);
                break;
            }
            Locator totalTickets = page.locator("//form[contains(@class,'space-y-4')]/div[1]/div/button[2]");
            PlaywrightAssertions.assertThat(totalTickets).isVisible();
            component.clickElement(totalTickets);
        }
    }

    public void verifyFullName(String value) {
        Locator name = page.locator(fullName);
        if (name.isVisible() && name.isEditable()) {
            component.fillTextElement(name, value);
            System.out.println("Full name is entered");
        } else {
            System.out.println("Error occurred");
        }
    }

    public void verifyEmail(String value) {
        Locator emailValue = page.locator(email);
        if (emailValue.isVisible() && emailValue.isEditable()) {
            component.fillTextElement(emailValue, value);
            System.out.println("Email is entered");
        } else {
            System.out.println("Error occurred");
        }
    }

    public void verifyPhoneNumber(String value) {
        Locator phone = page.locator(phoneNumber);
        if (phone.isVisible() && phone.isEditable()) {
            component.fillTextElement(phone, value);
            System.out.println("Phone number is entered");
        } else {
            System.out.println("Error occurred");
        }
    }

    public void verifyConfirmBookingButton() {
        Locator button = page.locator(confirmBookingButton);
        if (button.isVisible()) {
            component.clickElement(button);
            System.out.println("Clicked on the button");
        } else {
            System.out.println("Error occurred");
        }
    }

    public void verifyBooking() {
        Locator bookingMessage = page.locator(booking);
        if (bookingMessage.isVisible()) {
            if (bookingMessage.innerText().contains("Booking Confirmed!")) {
                System.out.println("Booking is confirmed");
            } else {
                System.out.println("Booking is not confirmed");
            }
        }
    }

    @Override
    public void confirmBooking(int tickets, String name, String email, String phoneNumber) throws IOException {
        verifyTickets(tickets);
        verifyFullName(name);
        verifyEmail(email);
        verifyPhoneNumber(phoneNumber);
        verifyConfirmBookingButton();
        verifyBooking();
    }


}
