package mockingWeb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.mockito.Mockito.*;

public class WebDriverMockTest {
    private WebDriver mockWebDriver;
    private WebElement mockWebElement;
    private HomePage homePage;

    @BeforeEach
    void setUp() {
        // Mock WebDriver and WebElement
        mockWebDriver = mock(WebDriver.class);
        mockWebElement = mock(WebElement.class);

        // Initialize HomePage with mocked WebDriver
        homePage = new HomePage(mockWebDriver);
    }

    @Test
    void testEnterUsername() {
        // Arrange
        when(mockWebDriver.findElement(By.name("user-name"))).thenReturn(mockWebElement);

        // Act
        homePage.enterUsername("mockUser");

        // Assert
        verify(mockWebElement).sendKeys("mockUser");
    }

    @Test
    void testGetErrorMessageText() {
        // Arrange
        when(mockWebDriver.findElement(By.cssSelector("h3[data-test='error']"))).thenReturn(mockWebElement);
        when(mockWebElement.getText()).thenReturn("Mock Error Message");

        // Act
        String errorMessage = homePage.getErrorMessageText();

        // Assert
        verify(mockWebDriver).findElement(By.cssSelector("h3[data-test='error']"));
        verify(mockWebElement).getText();
        assert errorMessage.equals("Mock Error Message");
    }
}
