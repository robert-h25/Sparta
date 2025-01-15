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

public class LoginStepdefs {

    private Website website;
    private static final String BASE_URL = "https://www.saucedemo.com/";

    @Before
    public void setup() throws IOException {
        TestSetup.startChromeService();
        TestSetup.createWebDriver();
    }

    @After
    public void afterEach(){
        TestSetup.quitWebDriver();
        TestSetup.stopService();
    }

    @Given("I am on the homepage")
    public void iAmOnTheHomepage() {
        website = TestSetup.getWebsite(BASE_URL);
    }

    @And("I have entered the username {string}")
    public void iHaveEnteredTheUsername(String arg0) {
        website.getHomePage().enterUsername(arg0);
    }

    @And("I have entered the password {string}")
    public void iHaveEnteredThePassword(String arg0) {
        website.getHomePage().enterPassword(arg0);
    }

    @When("I click on the login button")
    public void iClickOnTheLoginButton() {
        website.getHomePage().clickLoginButton();
    }

    @Then("I should land on the inventory page")
    public void iShouldLandOnTheInventoryPage() {
        MatcherAssert.assertThat(website.getCurrentUrl(), Matchers.is(BASE_URL + "inventory.html"));
    }

    @Then("I should see an error message that contains {string}")
    public void iShouldSeeAnErrorMessageThatContains(String arg0) {
        MatcherAssert.assertThat(website.getHomePage().isErrorMessageDisplayed(), Matchers.is(true));
        MatcherAssert.assertThat(website.getHomePage().getErrorMessageText(), Matchers.containsString(arg0));
    }
}
