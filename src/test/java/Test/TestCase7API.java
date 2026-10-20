package Test;

import APITest.LoginRequest;
import APITest.createEventRequest;
import Configuration.PropertiesFile;
import com.jayway.jsonpath.JsonPath;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;

public class TestCase7API {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext browserContext;
    protected Page page;
    PropertiesFile propertiesFile = new PropertiesFile();

    LoginRequest loginRequest = new LoginRequest();
    createEventRequest createEvent = new createEventRequest();

    APIRequestContext apiRequest;
    APIResponse response;
    String token;

    public void login() {
        //Serialization
        loginRequest.setEmail("Test1235@gmail.com");
        loginRequest.setPassword("Test@1234");

        playwright = Playwright.create();
        apiRequest = playwright.request().newContext();
        response = apiRequest.post("https://api.eventhub.rahulshettyacademy.com/api/auth/login",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData(loginRequest));
        System.out.println(response.ok());
        token = JsonPath.read(response.text(), "$.token");
        System.out.println("Bearer token retrieved from the login response" + token);
    }

    public void createEvent() {
        //Serialization
        createEvent.setTitle("Tech Summit 2026");
        createEvent.setDescription("Tech Summit 2026");
        createEvent.setCategory("Workshop");
        createEvent.setVenue("Hyderabad");
        createEvent.setCity("Hyderabad");
        createEvent.setEventDate("2026-12-15T09:00:00.000Z");
        createEvent.setPrice("1500");
        createEvent.setTotalSeats("20");
        createEvent.setImageUrl("https://example.com/banner.jpg");

        apiRequest = playwright.request().newContext();
        response = apiRequest.post("https://api.eventhub.rahulshettyacademy.com/api/events",
                RequestOptions.create()
                        .setHeader("Authorization", "Bearer " + token)
                        .setHeader("Content-Type", "application/json")
                        .setData(createEvent));
        String msg = JsonPath.read(response.text(), "$.message");
        System.out.println(msg);
        //System.out.println("Status : " + response.status());
        //System.out.println("Body   : " + response.text());
    }

    public static void main(String[] args) {
        TestCase7API test = new TestCase7API();
        test.login();
        test.createEvent();
    }

}
