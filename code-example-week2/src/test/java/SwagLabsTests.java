import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

public class SwagLabsTests {
    private static final String DRIVER_LOCATION = "src/test/resources/chromedriver.exe";
    private static final String BASE_URL = "https://www.saucedemo.com/";

    private static ChromeDriverService service;

    private static WebDriver webDriver;

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
    static void afterAll() throws IOException {
        service.stop();
    }

    @BeforeEach
    void setUp() {
        webDriver = new RemoteWebDriver(service.getUrl(), getChromeOptions());

    }

    @AfterEach
    void afterEach() {
        webDriver.quit();
    }

    @Test
    @DisplayName("Check the webdriver works")
    void checkWebdriverWorks() throws IOException {
        webDriver.get(BASE_URL);
        Assertions.assertEquals(BASE_URL, webDriver.getCurrentUrl());
        Assertions.assertEquals("Swag Labs", webDriver.getTitle());
    }

}
