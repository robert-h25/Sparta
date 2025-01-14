package testframewrk.tests;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import testframewrk.pages.Inventory;

import java.time.Duration;


public class POMSwagLabsTests extends TestSetup{
    private static final String BASE_URL = "https://www.saucedemo.com/";


    private void login(String username, String password) {

        homePage.enterUsername(username);
        homePage.enterPassword(password);
        homePage.clickLoginButton();
    }

    @Test
    @DisplayName("Given I enter a valid Username+Password, When i click login, Then i should land on the inventory page")
    void successfulLoginTest(){
        // Arrange
        webDriver.get(BASE_URL);

        // Act
        login("standard_user","secret_sauce");

        // Assert
        MatcherAssert.assertThat(website.getCurrentUrl(), Matchers.is(BASE_URL + "inventory.html"));
    }

    @Test
    @DisplayName("Given I enter an invalid Username+Password, When I click login, Then I should see an error message")
    void unsuccessfulLoginTest() {
        // Arrange
        webDriver.get(BASE_URL);

        // Act
        login("user","password");

        // Assert
        MatcherAssert.assertThat(homePage.isErrorMessageDisplayed(), Matchers.is(true));
        MatcherAssert.assertThat(homePage.getErrorMessageText(), Matchers.containsString("Username and password do not match any user in this service"));

    }

    @Test
    @DisplayName("Given I am logged in, when I view the inventory page, I should see the correct number of products")
    void checkNumberOfProductsOnInventoryPage() {
        // Arrange
        webDriver.get(BASE_URL);

        // Act
        login("standard_user","secret_sauce");
        Inventory inventory = website.getInventoryPage();
        int productCount = inventory.getProductCount();

        // Assert
        MatcherAssert.assertThat(productCount, Matchers.is(6));

    }

    @Test
    @DisplayName("Given I am logged in, when I view the inventory page, I should see the correct number of products (with wait)")
    void checkNumberOfProductsOnInventoryPage_WithWait() {
        // Arrange
        Wait<WebDriver> webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriver.get(BASE_URL);

        // Act
        login("standard_user","secret_sauce");
        webDriverWait.until(driver -> driver.getCurrentUrl().contains("/inventory"));

        // Assert
        webDriverWait.until(driver -> driver.getCurrentUrl().contains("/inventory"));
        MatcherAssert.assertThat(webDriver.getCurrentUrl(), Matchers.is("https://www.saucedemo.com/inventory.html"));
    }
}
