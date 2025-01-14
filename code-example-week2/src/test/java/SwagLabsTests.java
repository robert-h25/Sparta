import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import java.util.regex.Matcher;

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

    @Test
    @DisplayName("Given I enter a valid Username+Password, When i click login, Then i should land on the inventory page")
    void successfulLoginTest(){
        // Arrange
        webDriver.get(BASE_URL);
        WebElement username = webDriver.findElement(By.name("user-name"));
        WebElement password = webDriver.findElement(By.name("password"));
        WebElement loginButton = webDriver.findElement(By.id("login-button"));

        // Act
        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        loginButton.click();

        // Assert
        MatcherAssert.assertThat(webDriver.getCurrentUrl(), Matchers.is(BASE_URL+"inventory.html"));
    }

    @Test
    @DisplayName("Given I enter an invalid Username+Password, When I click login, Then I should see an error message")
    void unsuccessfulLoginTest() {
        // Arrange
        webDriver.get(BASE_URL);
        WebElement username = webDriver.findElement(By.name("user-name"));
        WebElement password = webDriver.findElement(By.name("password"));
        WebElement loginButton = webDriver.findElement(By.id("login-button"));

        // Act
        username.sendKeys("user");
        password.sendKeys("password");
        loginButton.click();

        // Assert
        WebElement errorMessage = webDriver.findElement(By.cssSelector("h3[data-test='error']"));
        MatcherAssert.assertThat(errorMessage.isDisplayed(), Matchers.is(true));
        MatcherAssert.assertThat(errorMessage.getText(), Matchers.containsString("Username and password do not match any user in this service"));
    }

    @Test
    @DisplayName("Given I am logged in, when I view the inventory page, I should see the correct number of products")
    public void checkNumberOfProductsOnInventoryPage(){
        //Arrange
        webDriver.get(BASE_URL);
        WebElement usernameField = webDriver.findElement(By.name("user-name"));
        WebElement passwordField = webDriver.findElement(By.name("password"));
        WebElement loginButton = webDriver.findElement(By.id("login-button"));

        //Act
        usernameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");
        loginButton.click();

        //Assert
        List<WebElement> products = webDriver.findElements(By.className("inventory_item"));
        int productsCount = products.size();
        MatcherAssert.assertThat(productsCount , Matchers.is(6));
    }

    @Test
    @DisplayName("Given I am logged in, when I view the inventory page, I should see the correct number of products (with wait)")
    public void checkNumberOfProductsOnInventnoryPage_WithWait(){

        Wait<WebDriver> webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriver.get(BASE_URL);
        WebElement usernameField= webDriver.findElement(By.name("user-name"));
        WebElement passwordField= webDriver.findElement(By.name("password"));

        usernameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce", Keys.ENTER);

        webDriverWait.until(driver -> driver.getCurrentUrl().contains("/inventory"));
        MatcherAssert.assertThat(webDriver.getCurrentUrl(), Matchers.is("https://www.saucedemo.com/inventory.html"));

    }

}
