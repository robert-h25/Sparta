package mockingWeb;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class WebDriverMockTest {

    private WebDriver mockWebDriver;
    private WebElement mockWebElement;
    private HomePage mockHomePage;
    private Website mockWebsite;
    private WebElement mockUsernameField;
    private WebElement mockPasswordField;
    private WebElement mockLoginButton;

    private String Base_url = "https://www.saucedemo.com/";

    @BeforeEach
    void setUp() {
        mockWebDriver = mock(WebDriver.class);
        mockWebElement = mock(WebElement.class);
        mockHomePage = new HomePage(mockWebDriver);
        mockUsernameField = mock(WebElement.class);
        mockPasswordField = mock(WebElement.class);
        mockLoginButton = mock(WebElement.class);
        mockWebsite = new Website(mockWebDriver);
    }

    private void login(String username, String password) {

        mockHomePage.enterUsername(username);
        mockHomePage.enterPassword(password);
        mockHomePage.clickLoginButton();
    }

    private void mockElements(){
        when(mockWebDriver.findElement(By.name("user-name"))).thenReturn(mockUsernameField);
        when(mockWebDriver.findElement(By.name("password"))).thenReturn(mockPasswordField);
        when(mockWebDriver.findElement(By.id("login-button"))).thenReturn(mockLoginButton);
    }

    @Test
    void successfulLoginTest() {

        // Arrange
        mockElements();

        // Act
        login("standard_user","secret_sauce");

        // Assert
        verify(mockUsernameField).sendKeys("standard_user");
        verify(mockPasswordField).sendKeys("secret_sauce");
        verify(mockLoginButton).click();
    }

    @Test
    void unsuccessfulLoginTest(){
        // Arrange
        mockElements();

        // Act
        login("user","sauce");

        // Assert
        verify(mockUsernameField).sendKeys("user");
        verify(mockPasswordField).sendKeys("sauce");
        verify(mockLoginButton).click();
    }

    @Test
    void testGetErrorMessageText() {
        // Arrange
        when(mockWebDriver.findElement(By.cssSelector("h3[data-test='error']"))).thenReturn(mockWebElement);
        when(mockWebElement.getText()).thenReturn("Mock Error Message");

        // Act
        String errorMessage = mockHomePage.getErrorMessageText();

        // Assert
        verify(mockWebDriver).findElement(By.cssSelector("h3[data-test='error']"));
        verify(mockWebElement).getText();
        assertThat(errorMessage, containsString("Mock Error Message"));
    }
    @Test
    void testGetCurrentUrl() {
        // Arrange
        when(mockWebDriver.getCurrentUrl()).thenReturn(Base_url);

        // Act
        String currentUrl = mockWebsite.getCurrentUrl();

        // Assert
        verify(mockWebDriver).getCurrentUrl();
        assertThat(currentUrl, is(Base_url));
    }

    @Test
    void testGetPageTitle() {
        // Arrange
        when(mockWebDriver.getTitle()).thenReturn("Mock Page Title");

        // Act
        String pageTitle = mockWebsite.getPageTitle();

        // Assert
        verify(mockWebDriver).getTitle();
        assertThat(pageTitle, is("Mock Page Title"));
    }

    @Test
    void checkNumberOfProductsOnInventoryPage_WithWait() {

        // Arrange
        WebDriverWait webDriverWait = new WebDriverWait(mockWebDriver, Duration.ofSeconds(10));

        mockElements();

        when(mockWebDriver.getCurrentUrl()).thenReturn(Base_url);
        when(mockWebDriver.getCurrentUrl()).thenReturn(Base_url+"inventory");

        // Act
        login("standard_user", "secret_sauce");
        webDriverWait.until(driver -> driver.getCurrentUrl().contains("/inventory"));

        // Assert
        verify(mockWebDriver, times(1)).getCurrentUrl();
        assertThat(mockWebDriver.getCurrentUrl(), is(Base_url+"inventory"));
    }
}
