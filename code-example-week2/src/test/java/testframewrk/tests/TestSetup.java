package testframewrk.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
    public HomePage homePage;

    static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        //options.addArguments("--headless");
        return options;
    }

    @BeforeAll
    static void beforeAll() throws IOException {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(DRIVER_LOCATION))
                .usingAnyFreePort()
                .build();
        service.start();
    }

    @AfterAll
    static void afterAll() {
        service.stop();
    }

    @BeforeEach
    void setUp() {
        webDriver = new RemoteWebDriver(service.getUrl(), getChromeOptions());
        website = new Website(webDriver);
        homePage = website.getHomePage();


    }

    @AfterEach
    void afterEach() {
        webDriver.quit();
    }

    static Website getWebsite(String url) {
        webDriver.get(url);
        return new Website(webDriver);
    }
}
