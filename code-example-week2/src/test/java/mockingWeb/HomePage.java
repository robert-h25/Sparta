package mockingWeb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver webDriver;

    private By username = new By.ByName("user-name");
    private By password = new By.ByName("password");
    private By loginButton = new By.ById("login-button");
    private By errorMessage = new By.ByCssSelector("h3[data-test='error']");

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void enterUsername(String username) {
        webDriver.findElement(this.username).sendKeys(username);
    }

    public void enterPassword(String password) {
        webDriver.findElement(this.password).sendKeys(password);
    }

    public void clickLoginButton() {
        webDriver.findElement(this.loginButton).click();
    }

    public String getErrorMessageText() {
        return webDriver.findElement(this.errorMessage).getText();
    }

    public boolean isErrorMessageDisplayed() {
        return webDriver.findElement(this.errorMessage).isDisplayed();
    }
}
