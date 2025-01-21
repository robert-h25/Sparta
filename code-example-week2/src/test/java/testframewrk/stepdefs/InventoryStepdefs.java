package testframewrk.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import testframewrk.pages.Website;

import java.io.IOException;

public class InventoryStepdefs {
    private static final String BASE_URL = "https://www.saucedemo.com/";
    private LoginStepdefs loginStepdefs = new LoginStepdefs();
    private int cartCount;


    public void background(){
        loginStepdefs.iAmOnTheHomepage();
        loginStepdefs.iHaveEnteredTheUsername("standard_user");
        loginStepdefs.iHaveEnteredThePassword("secret_sauce");
        loginStepdefs.iClickOnTheLoginButton();
    }

    @When("I land on the inventory page")
    public void iLandOnTheInventoryPage() {
        background();
        MatcherAssert.assertThat(loginStepdefs.website.getCurrentUrl(), Matchers.is(BASE_URL + "inventory.html"));
    }

    @Then("The number of available products is {int}")
    public void theNumberOfAvailableProductsIs(int expectedProductCount) {
        int productCount = loginStepdefs.website.getInventoryPage().getProductCount();
        MatcherAssert.assertThat(productCount, Matchers.is(expectedProductCount));
    }

    @When("I add an item to the cart")
    public void iAddAnItemToTheCart() {
        cartCount = loginStepdefs.website.getInventoryPage().cartCount();
        loginStepdefs.website.getInventoryPage().clickAddToCartButton();
    }

    @Then("The item count increases by one")
    public void theItemCountIncreasesByOne() {
        MatcherAssert.assertThat(loginStepdefs.website.getInventoryPage().cartCount(),Matchers.is(cartCount+1));
    }
}
