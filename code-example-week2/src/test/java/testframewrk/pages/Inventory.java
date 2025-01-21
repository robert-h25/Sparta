package testframewrk.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Inventory {

    private WebDriver webDriver;

    private By productItems = By.className("inventory_item");
    private By addToCartButton = By.className("btn_inventory");
    private By cartCount = By.className("shopping_cart_badge");

    public Inventory(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public List<WebElement> getProducts() {
        return webDriver.findElements(this.productItems);
    }

    public void clickAddToCartButton() {
        List<WebElement> cartButtons = webDriver.findElements(this.addToCartButton);
        if (!cartButtons.isEmpty()) {
            cartButtons.getFirst().click();
        }
    }

    public int cartCount() {
        try {
            return Integer.parseInt(webDriver.findElement(this.cartCount).getText());
        }
        catch (Exception e) {
            return 0;
        }
    }

    public int getProductCount() {
        return getProducts().size();
    }
}
