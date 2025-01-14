package testframewrk.pages;

import org.openqa.selenium.WebDriver;

public class Website {
    private WebDriver webDriver;
    private HomePage homePage;
    private Inventory inventory;

    public Website(WebDriver webDriver) {
        this.webDriver = webDriver;
        homePage = new HomePage(webDriver);
        inventory = new Inventory(webDriver);
    }

    public HomePage getHomePage(){
        return  homePage;
    }
    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    public String getPageTitle() {
        return webDriver.getTitle();
    }

    public Inventory getInventoryPage() {
        return inventory;
    }
}
