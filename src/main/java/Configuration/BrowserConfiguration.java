package Configuration;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import javax.naming.Context;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;

public class BrowserConfiguration extends DatabaseConfiguration {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext browserContext;
    protected Page page;
    PropertiesFile propertiesFile = new PropertiesFile();

    @Parameters("Browser")
    @BeforeMethod
    public void browserSetup(String Browser) throws IOException {

        String url = propertiesFile.readConfigProperties("URL");
        //String Browser = propertiesFile.readConfigProperties("Browser");

        playwright = Playwright.create();

        if (Browser.equalsIgnoreCase("chromium")) {
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false).setSlowMo(500));
        } else if (Browser.equalsIgnoreCase("firefox")) {
            browser = playwright.firefox().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false).setSlowMo(500));
        } else if (Browser.equalsIgnoreCase("webkit")) {
            browser = playwright.webkit().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false).setSlowMo(500));
        } else {
            System.out.println("Invalid browser specified. Please use 'chromium', 'firefox', or 'webkit'.");
            throw new IllegalArgumentException("Invalid browser specified: " + Browser);
        }

        browserContext = browser.newContext();
        startTracing();
        page = browserContext.newPage();
        page.navigate(url);
        System.out.println("Navigated to" + url);
    }

    @AfterMethod
    public void closeBrowserSetup() throws SQLException {
        if (page != null) {
            stopTracing();
            page.close();
            browserContext.close();
            browser.close();
            playwright.close();
            System.out.println("Session is closed");

        } else {
            System.out.println("Page is null, cannot close the browser.");
        }
    }

    public void startTracing() {
        if (browserContext == null) {
            throw new IllegalStateException("Browser context is not initialized.");
        }

        browserContext.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
    }

    public void stopTracing()
    {
        browserContext.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("Tracing", "trace_" + System.currentTimeMillis() + ".zip")));
    }

}
