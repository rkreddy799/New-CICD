package SeleniumFrameworkDesignRevision.stepdefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumFrameworkDesignRevision.PageObejcts.ConfirmationPage;
import SeleniumFrameworkDesignRevision.PageObejcts.LandingPage;
import SeleniumFrameworkDesignRevision.PageObejcts.PaymentPage;
import SeleniumFrameworkDesignRevision.PageObejcts.ProductCatalogue;
import SeleniumFrameworkDesignRevision.PageObejcts.SuccessPage;
import SeleniumFrameworkDesignRevision.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepdefinition extends BaseTest {
    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public SuccessPage successPage;

    @Given("I landed on E-commerce page")
    public void iLandedOnEcommercePage() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^logged in with username (.+) and password (.+)$")
    public void loggedWithUsernameAndPassword(String username, String password) {
        productCatalogue = landingPage.loginaplication(username, password);
    }

    @When("^I add item (.+) to the cart$")
    public void iAddItemToCart(String productName) {
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addItemToCart(productName);
    }

    @And("^I checkout (.+) and complete order creation (.+)$")
    public void checkoutAndCompleteOrderCreation(String productName, String countryName) {
        PaymentPage paymentPage = productCatalogue.goToCartPage();
        boolean match = paymentPage.productNameCheck(productName);
        Assert.assertTrue(match);

        ConfirmationPage confirmationPage = paymentPage.gotoCheckOut();
        successPage = confirmationPage.countryName(countryName);
    }

    @Then("{string} message displays")
    public void messagedisplays(String string) {
        String thankYouMsg = successPage.getSuccessmsg();
        Assert.assertEquals(thankYouMsg, string);
        driver.close();    }

    @Then("verify error message display")
    public void verifyErrorMessageDisplay() {
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
        driver.close();
    }
}
