package testframewrk.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Inventory {

    private WebDriver webDriver;

    private By productItems = By.className("inventory_item");

    public Inventory(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public List<WebElement> getProducts() {
        return webDriver.findElements(this.productItems);
    }

    public int getProductCount() {
        return getProducts().size();
    }
}
