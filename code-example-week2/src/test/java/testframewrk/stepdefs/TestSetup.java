package testframewrk.stepdefs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import testframewrk.pages.HomePage;
import testframewrk.pages.Website;

import java.io.File;
import java.io.IOException;

public class TestSetup {
    private static final String DRIVER_LOCATION = "src/test/resources/chromedriver.exe";

    private static ChromeDriverService service;

    public static WebDriver webDriver;
    public static Website website;
    public static HomePage homePage;

    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        //options.addArguments("--headless");
        return options;
    }

    public static void startChromeService() throws IOException {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(DRIVER_LOCATION))
                .usingAnyFreePort()
                .build();
        service.start();
    }

    static void stopService() {
        service.stop();
    }

    public static void createWebDriver() {
        webDriver = new RemoteWebDriver(service.getUrl(), getChromeOptions());
        website = new Website(webDriver);
        homePage = website.getHomePage();


    }

    public static void quitWebDriver() {
        webDriver.quit();
    }

    static Website getWebsite(String url) {
        webDriver.get(url);
        return new Website(webDriver);
    }
}
